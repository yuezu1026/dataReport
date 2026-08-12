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

import com.github.pagehelper.Page;
import com.vat.bean.User;


@Mapper
public interface UserMapper {

    public void insertUser(@Param("user") User user);
    
    public void reviewUser(@Param("user") User user);
    

    public void update(User user);
    
    public void delete(int id);
    
    public User findUserPassword(@Param("username") String userName,@Param("password") String password);
    
    public User findUser(@Param("username") String userName);
    
    public User findUserById(@Param("userId") String userId);
    
    public Page<User> findUserByPage(@Param("user") User user );
    
    
    
}