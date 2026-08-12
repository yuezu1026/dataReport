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

import com.github.pagehelper.Page;
import com.vat.bean.User;

public interface UserService {

    public void insertUser(User user);
    
    public User userLogin(String userName,String password);
    
    public User findUser(String userName);
    
    public User findUserById(String userId);
    
    public void reviewUser(User user);
    
    public Page<User> findUserByPage(int currentPage, int pageSize);
    
}
