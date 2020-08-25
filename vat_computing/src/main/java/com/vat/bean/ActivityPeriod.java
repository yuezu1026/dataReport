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
