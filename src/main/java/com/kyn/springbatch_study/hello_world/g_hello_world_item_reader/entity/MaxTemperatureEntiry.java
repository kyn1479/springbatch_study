package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.entity;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/25
 */
public class MaxTemperatureEntiry {
	private String siteId;
	private String type;
	private String date;
	private String temperature;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MaxTemperatureEntiry [siteId=" + siteId + ", type=" + type + ", date=" + date + ", temperature="
				+ temperature + "]";
	}
}
