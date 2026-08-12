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
package com.vat.bean;

import java.io.Serializable;

public class ActivityPeriod implements Serializable {

    private static final long serialVersionUID = -3493637804929497260L;

    private String activityPeriodId;
    private String userId;
    private String dataType;
    private String activityPeriod;

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getActivityPeriod() {
	return activityPeriod;
    }

    public void setActivityPeriod(String activityPeriod) {
	this.activityPeriod = activityPeriod;
    }

    public String getActivityPeriodId() {
	return activityPeriodId;
    }

    public void setActivityPeriodId(String activityPeriodId) {
	this.activityPeriodId = activityPeriodId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
