package com.mountainseed.datapoint.controller;



import com.mountainseed.datapoint.model.DataPoint;
import com.mountainseed.datapoint.service.DataPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.Optional;

import javax.ws.rs.WebApplicationException;

@RestController
@EnableWebMvc
public class DataPointController {
	
	@Autowired
	DataPointService dataPointService;
	
    @RequestMapping(path = "/dps", method = RequestMethod.POST)
    public DataPoint createDataPoints(@RequestBody DataPoint newDataPoint) {
        if (newDataPoint.getPropertyAddress() == null || newDataPoint.getPropertyType() == null) {
            return null;
        }

        return this.dataPointService.createDataPoint(newDataPoint);
    }

    @RequestMapping(path = "/dps", method = RequestMethod.GET)
    public DataPoint[] listDataPointss(@RequestParam("limit") Optional<Integer> limit) {
        int queryLimit = 3;
        
        // need to check limit is not bigger than certain value
        if (limit.isPresent() && limit.get() > 100)
        	throw new WebApplicationException("Unprocessable Entity", 422);
        if (limit.isPresent()) {
            queryLimit = limit.get() + 1;
        }

        return this.dataPointService.getDataPoints(queryLimit);
    }

    @RequestMapping(path = "/dps/{dpId}", method = RequestMethod.GET)
    public DataPoint listDataPoints(@PathVariable("dpId") String dpId) {

    	return this.dataPointService.getDataPoint(dpId);
    }

}
