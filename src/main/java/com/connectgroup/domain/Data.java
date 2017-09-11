package com.connectgroup.domain;

import java.util.Date;

public class Data {

	private Date requestTimeStamp;
	private String countryCode;
	private Long responseTime;

	public Date getRequestTimeStamp() {
		return requestTimeStamp;
	}

	public void setRequestTimeStamp(Date requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((requestTimeStamp == null) ? 0 : requestTimeStamp.hashCode());
		result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (requestTimeStamp == null) {
			if (other.requestTimeStamp != null)
				return false;
		} else if (!requestTimeStamp.equals(other.requestTimeStamp))
			return false;
		if (responseTime == null) {
			if (other.responseTime != null)
				return false;
		} else if (!responseTime.equals(other.responseTime))
			return false;
		return true;
	}

	@Override
	

	public String toString() {
		return "Data [requestTimeStamp=" + requestTimeStamp + ", countryCode=" + countryCode + ", responseTime="
				+ responseTime + "]";
	}
  
}
