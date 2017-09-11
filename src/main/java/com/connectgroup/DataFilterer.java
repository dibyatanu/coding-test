package com.connectgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.connectgroup.domain.Data;

public class DataFilterer {
	
	
	public static Collection<?> filterByCountry(Reader source, String country) {
		List<Data> listofData = convertSourceToDataList(source);
		return  listofData.stream()
				.filter(data -> data.getCountryCode().equalsIgnoreCase(country))
				.collect(Collectors.toList());

	}

	public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
		List<Data> listofData = convertSourceToDataList(source);
		return  listofData.stream()
				.filter(data -> data.getCountryCode().equalsIgnoreCase(country))
				.filter(data -> data.getResponseTime() > limit)
				.collect(Collectors.toList());
	}

	public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
		
		List<Data> listofData = convertSourceToDataList(source);
		
		if(listofData.isEmpty())
		{
			return Collections.emptyList();
		}
		
		// Calculate average response time 
		Double averageReponseTime=listofData.stream().mapToDouble(data->data.getResponseTime()).average().getAsDouble();
		return listofData.stream()
				.filter(data-> data.getResponseTime()>averageReponseTime)
				.collect(Collectors.toList());
	}
	
	
	
	private static  List<Data> convertSourceToDataList(final Reader source)
	{
		List<Data> listofData = new ArrayList<>();

		try (BufferedReader buffReader = new BufferedReader(source)) {
			String line = buffReader.readLine();

			if (line == null || line.isEmpty()) {
				return Collections.emptyList();
			}

			// convert content to domain object

			while ((line = buffReader.readLine()) != null) {

				Data data = new Data();
				String splitString[] = line.split(",");
				long unixTimeStamp = Long.parseLong(splitString[0]);
				Date date = new Date(unixTimeStamp * 1000L);
				data.setRequestTimeStamp(date);
				data.setCountryCode(splitString[1]);
				data.setResponseTime(Long.parseLong(splitString[2]));
				listofData.add(data);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return listofData;
	}
}