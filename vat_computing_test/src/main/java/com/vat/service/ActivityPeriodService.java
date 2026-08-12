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
package com.vat.service;

import java.util.List;

import com.vat.bean.ActivityPeriod;

public interface ActivityPeriodService {
    
    boolean insertActivityPeriod(ActivityPeriod activityPeriod) throws Exception;
    
    boolean insertActivityPeriod(String userId, List<String> activityPeriodList,String dataType) throws Exception;
    
    List<ActivityPeriod> findActivityPeriod(String userId,String dataType) throws Exception;
    
    boolean deleteActivityPeriod(String userId,String dataType) throws Exception;
}
