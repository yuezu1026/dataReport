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
package com.vat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.SystemUser;


@Mapper
public interface SystemUserMapper {

    public void insertSystemUser(@Param("user") SystemUser user);
    
    public SystemUser findSystemUserPassword(@Param("username") String userName,@Param("password") String password);
    
    public SystemUser findSystemUser(@Param("username") String userName);
    
}