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

public class PayAttach {
	//商户订单号
	private String orderId;
	//课程编号
	private int courseId;
	//购买的课时数量
	private int couresCount;
	
	
	
	public PayAttach(String orderId, int courseId, int couresCount) {
		this.orderId = orderId;
		this.courseId = courseId;
		this.couresCount = couresCount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCouresCount() {
		return couresCount;
	}
	public void setCouresCount(int couresCount) {
		this.couresCount = couresCount;
	}
	
	
}
