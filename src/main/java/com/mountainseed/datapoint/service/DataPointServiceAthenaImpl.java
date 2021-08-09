package com.mountainseed.datapoint.service;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mountainseed.datapoint.model.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Service
//@Profile("athena")
@Primary
public class DataPointServiceAthenaImpl implements DataPointService {

	private static final Logger LOG = LoggerFactory.getLogger(DataPointServiceAthenaImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public DataPoint createDataPoint(DataPoint newDataPoint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataPoint[] getDataPoints(Integer limit) {
		
		String querystr = "select * from ms_data.lender_cast_report limit " + limit;
				
		List<Map<String, Object>> list = jdbcTemplate.queryForList(querystr);
		List<DataPoint> dps = new ArrayList<DataPoint>();
		list.remove(0);
		list.forEach(x -> {
			try {
				dps.add(this.transformAthenaResponse(x));
			} catch (JSONException | ParseException e) {
				LOG.error(e.getLocalizedMessage());
			}
		});
		
		return dps.toArray(new DataPoint[0]);
	}

	@Override
	public DataPoint getDataPoint(String dpId) {
		// TODO Auto-generated method stub
		return null;
	}

	private DataPoint transformAthenaResponse(Object jsonString) throws JSONException, ParseException {
		
		DataPoint dp = new DataPoint();
		
		LOG.info(jsonString.toString());
		JSONObject obj = new JSONObject((Map)jsonString);
		
		dp.setId(obj.getString("file_id"));
		dp.setPropertyAddress(obj.getString("street_address"));
		dp.setPropertyType(obj.getString("property_type"));
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		dp.setReportDate(formatter.parse(obj.getString("complete_date")));
		return dp;
	}
}
