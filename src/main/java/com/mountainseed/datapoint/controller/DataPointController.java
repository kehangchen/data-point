package com.mountainseed.datapoint.controller;



import com.mountainseed.datapoint.model.DataPoint;
import com.mountainseed.datapoint.model.DataPointRecord;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


@RestController
@EnableWebMvc
public class DataPointController {
    @RequestMapping(path = "/dps", method = RequestMethod.POST)
    public DataPoint createDataPoints(@RequestBody DataPoint newDataPoint) {
        if (newDataPoint.getPropertyAddress() == null || newDataPoint.getPropertyType() == null) {
            return null;
        }

        DataPoint dbDataPoint = newDataPoint;
        dbDataPoint.setId(UUID.randomUUID().toString());
        return dbDataPoint;
    }

    @RequestMapping(path = "/dps", method = RequestMethod.GET)
    public DataPoint[] listDataPointss(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        int queryLimit = 10;
        if (limit.isPresent()) {
            queryLimit = limit.get();
        }

        DataPoint[] outputDataPoints = new DataPoint[queryLimit];

        for (int i = 0; i < queryLimit; i++) {
            DataPoint newDataPoint = new DataPoint();
            newDataPoint.setId(UUID.randomUUID().toString());
            newDataPoint.setPropertyAddress(DataPointRecord.getRandomPropertyAddress());
            newDataPoint.setPropertyType(DataPointRecord.getRandomPropertyType());
            newDataPoint.setReportDate(DataPointRecord.getRandomReportDate());
            outputDataPoints[i] = newDataPoint;
        }

        return outputDataPoints;
    }

    @RequestMapping(path = "/dps/{dpId}", method = RequestMethod.GET)
    public DataPoint listDataPoints() {
        DataPoint newDataPoint = new DataPoint();
        newDataPoint.setId(UUID.randomUUID().toString());
        newDataPoint.setPropertyType(DataPointRecord.getRandomPropertyType());
        newDataPoint.setReportDate(DataPointRecord.getRandomReportDate());
        newDataPoint.setPropertyAddress(DataPointRecord.getRandomPropertyAddress());
        return newDataPoint;
    }

}
