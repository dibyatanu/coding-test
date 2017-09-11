package com.connectgroup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.connectgroup.domain.Data;



public class DataFiltererTest {
	
	private static final String MULTI_LINE_FILE="src/test/resources/multi-lines";
	private static final String SINGLE_LINE_FILE="src/test/resources/single-line";
	private static final String COUNTRY_CODE_GB="GB";
	private static final String COUNTRY_CODE_US="US";
	private static final Long TIME_LIMIT=539l;
	
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }
    
    @Test
    public void filterByCountry_ShouldReturnNonEmptyListOfData() throws FileNotFoundException
    {
    	/*arrange*/
    	
    	/*act*/
    	Collection<?> actualListOfDataGB=	DataFilterer.filterByCountry(openFile(MULTI_LINE_FILE), COUNTRY_CODE_GB);
    	
    	/*assert*/
    	assertThat(actualListOfDataGB.isEmpty(), is(false));
    	
    	
    }
    @Test
    public void filterByCountry_ShouldFilterByCode() throws FileNotFoundException
    {
    	/*act*/
    	Collection<?> actualListOfDataGB=	DataFilterer.filterByCountry(openFile(MULTI_LINE_FILE), COUNTRY_CODE_GB);
    	
    	/*assert*/
    	assertEquals(expectedListOfDataForGB(),actualListOfDataGB);
    }
    
    
    @Test
    public void filterByCountry_ShouldFilterByCode_WithSingleLogEntry() throws FileNotFoundException
    {
    	/*act*/
    	Collection<?> actualListOfDataGB=	DataFilterer.filterByCountry(openFile(SINGLE_LINE_FILE), COUNTRY_CODE_GB);
    	
    	/*assert*/
    	assertEquals(expectedListOfDataForGBSingleLog(),actualListOfDataGB);
    }
    @Test
   public void filterByCountryWithResponseTimeAboveLimit_ShouldReturnEmptyCollectionWhenLogFileIsEmpty() throws FileNotFoundException
   {
    	/*act*/  
    	assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/empty"), "GB",TIME_LIMIT).isEmpty());
   }
    
    @Test
    public void  filterByCountryWithResponseTimeAboveLimit_ShouldFilterByCountryCodeANdResponseTime() throws FileNotFoundException
    {
    	/*act*/
    	Collection<?> actualListOfDataUS= DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile(MULTI_LINE_FILE), COUNTRY_CODE_US, TIME_LIMIT);
    	
    	/*assert*/
    	
    	assertEquals(expectedListOfDataForUSAboveLimit(),actualListOfDataUS);
    }
    
    @Test
    public void filterByResponseTimeAboveAverage_ShouldReturnEmptyCollectionWhenLogFileIsEmpty() throws FileNotFoundException
    {
    	/*act*/
    	assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/empty")).isEmpty());
    }
    
    @Test
    public void filterByResponseTimeAboveAverage_ShouldReturnListOfDataWithAboveAverageReponseTime() throws FileNotFoundException
    {
    	/*act*/
    	Collection<?> actualListOfData= DataFilterer.filterByResponseTimeAboveAverage(openFile(MULTI_LINE_FILE));
    	
/*     /*assert*/
    	assertThat(actualListOfData.isEmpty(), is(false));
    }

    
    private List<?> expectedListOfDataForGB()
    {
    	List <Data> listDataGb= new ArrayList<>();
    	Data dataGb= new Data();
    	long unixTimeStamp = Long.parseLong("1432917066");
		Date date = new Date(unixTimeStamp * 1000L);
		dataGb.setRequestTimeStamp(date);
    	dataGb.setCountryCode("GB");
    	dataGb.setResponseTime(Long.parseLong("37"));
    	listDataGb.add(dataGb);
    	return Arrays.asList(dataGb);
    	
    }
    
    
    private List<?> expectedListOfDataForGBSingleLog()
    {
    	List <Data> listDataGb= new ArrayList<>();
    	Data dataGb= new Data();
    	long unixTimeStamp = Long.parseLong("1431592497");
		Date date = new Date(unixTimeStamp * 1000L);
		dataGb.setRequestTimeStamp(date);
    	dataGb.setCountryCode("GB");
    	dataGb.setResponseTime(Long.parseLong("200"));
    	listDataGb.add(dataGb);
    	return Arrays.asList(dataGb);
    	
    }
    
    
    
    private List<?> expectedListOfDataForUSAboveLimit()
    {
    	Data dataUS1= new Data();
    	long unixTimeStampUS1 = Long.parseLong("1433666287");
		Date dateUS1 = new Date(unixTimeStampUS1 * 1000L);
		dataUS1.setRequestTimeStamp(dateUS1);
		dataUS1.setCountryCode("US");
		dataUS1.setResponseTime(Long.parseLong("789"));
		
		Data dataUS2= new Data();
    	long unixTimeStampUS2 = Long.parseLong("1432484176");
		Date dateUS2 = new Date(unixTimeStampUS2 * 1000L);
		dataUS2.setRequestTimeStamp(dateUS2);
		dataUS2.setCountryCode("US");
		dataUS2.setResponseTime(Long.parseLong("850"));
		
    	
    	return Arrays.asList(dataUS1,dataUS2);
    	
    }
    
    private List<?> expectedListOfDataWithAboveAverageReponseTime()
    {
    	
    	
		Data dataUS3= new Data();
    	long unixTimeStampUS3 = Long.parseLong("1433190845");
		Date dateUS3 = new Date(unixTimeStampUS3 * 1000L);
		dataUS3.setRequestTimeStamp(dateUS3);
		dataUS3.setCountryCode("US");
		dataUS3.setResponseTime(Long.parseLong("539"));
		
    	Data dataUS1= new Data();
    	long unixTimeStampUS1 = Long.parseLong("1433666287");
		Date dateUS1 = new Date(unixTimeStampUS1 * 1000L);
		dataUS1.setRequestTimeStamp(dateUS1);
		dataUS1.setCountryCode("US");
		dataUS1.setResponseTime(Long.parseLong("789"));
		
		Data dataUS2= new Data();
    	long unixTimeStampUS2 = Long.parseLong("1432484176");
		Date dateUS2 = new Date(unixTimeStampUS2 * 1000L);
		dataUS2.setRequestTimeStamp(dateUS2);
		dataUS2.setCountryCode("US");
		dataUS2.setResponseTime(Long.parseLong("850"));
		
	
    	
    	return Arrays.asList(dataUS1,dataUS2,dataUS3);
    	
    }
    
    
    
    
    

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
    
}
