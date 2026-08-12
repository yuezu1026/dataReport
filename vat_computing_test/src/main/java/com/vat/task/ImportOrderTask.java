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
package com.vat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vat.service.ImportService;

public class ImportOrderTask implements Runnable{
    
    private final static Logger logger = LoggerFactory.getLogger(ImportOrderTask.class);
    
    private ImportService importService;
    
    private String filePath;
    
    private String userId;
    
    private String excelName;
    
    public ImportOrderTask(ImportService importService, String excelName, String filePath, String userId) {
	this.importService = importService ;
	this.filePath = filePath;
	this.userId = userId;
	this.excelName = excelName;
    }
    
    @Override
    public void run() {
	try {
         importService.batchImport(filePath,excelName, userId);
	}catch(Exception e) {
	    logger.error("error",e);
	}
    }
}