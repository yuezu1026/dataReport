/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vat.bean.weixin;

/**
 * @author Javen
 * 2016年4月15日
 */
public class WeixinOrder {
	private float total_fee;//总金额
	private String transaction_id;//订单号
	private String courseName;//课程名称
	private int couresCount;//课时数
	private String buyTime;//购买时间
	private String url;
	
	

	public WeixinOrder(float total_fee, String transaction_id, String courseName, int couresCount, String buyTime,
			String url) {
		this.total_fee = total_fee;
		this.transaction_id = transaction_id;
		this.courseName = courseName;
		this.couresCount = couresCount;
		this.buyTime = buyTime;
		this.url = url;
	}
	public float getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCouresCount() {
		return couresCount;
	}
	public void setCouresCount(int couresCount) {
		this.couresCount = couresCount;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
