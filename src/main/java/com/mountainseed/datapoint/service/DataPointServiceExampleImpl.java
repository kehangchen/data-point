package com.mountainseed.datapoint.service;

import com.mountainseed.datapoint.model.DataPoint;
import com.mountainseed.datapoint.model.DataPointRecord;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DataPointServiceExampleImpl implements DataPointService {

	@Override
	public DataPoint createDataPoint(DataPoint newDataPoint) {
		
		DataPoint dbDataPoint = newDataPoint;
        dbDataPoint.setId(UUID.randomUUID().toString());
        return dbDataPoint;
	}

	@Override
	public DataPoint[] getDataPoints(Integer limit) {
		
		DataPoint[] outputDataPoints = new DataPoint[limit];

        for (int i = 0; i < limit; i++) {
            DataPoint newDataPoint = new DataPoint();
            newDataPoint.setId(UUID.randomUUID().toString());
            newDataPoint.setPropertyAddress(DataPointRecord.getRandomPropertyAddress());
            newDataPoint.setPropertyType(DataPointRecord.getRandomPropertyType());
            newDataPoint.setReportDate(DataPointRecord.getRandomReportDate());
            outputDataPoints[i] = newDataPoint;
        }

        return outputDataPoints;
	}

	@Override
	public DataPoint getDataPoint(String dpId) {
		
		DataPoint newDataPoint = new DataPoint();
        newDataPoint.setId(UUID.randomUUID().toString());
        newDataPoint.setPropertyType(DataPointRecord.getRandomPropertyType());
        newDataPoint.setReportDate(DataPointRecord.getRandomReportDate());
        newDataPoint.setPropertyAddress(DataPointRecord.getRandomPropertyAddress());
        return newDataPoint;
	}

}
