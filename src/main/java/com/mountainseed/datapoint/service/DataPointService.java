package com.mountainseed.datapoint.service;

import com.mountainseed.datapoint.model.DataPoint;

public interface DataPointService {

	DataPoint createDataPoint(DataPoint newDataPoint);
	DataPoint[] getDataPoints(Integer limit);
	DataPoint getDataPoint(String dpId);
}
