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
package com.vat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class UploadConfig {
    
    //接收文件服务URL
    //@Value("${upload.RECEIVE_FILE_URL}")
    private String receiveFileUrl;
    
    //接收文件目录
    //@Value("${upload.RECEIVE_ROOT}")
    private String receiveRoot;
    
    //接收文件下载根域名地址
    //@Value("${upload.RECEIVE_DOWNLOAD_ROOT}")
    private String receiveDownloadRoot;
    
    //上传根目录
    @Value("${upload.UPLOAD_ROOT}")
    private String uploadRoot;
 
    //下载根域名地址
   @Value("${upload.DOWNLOAD_ROOT}")
    private String downloadRoot;
    
    //文件后缀
    //@Value("${upload.SUFFIXLIST}")
    private String suffixList;
    
    public String getParentPath( int index ) {
          if(index == 1) {
              return "/uboxsys" ;
         } else if (index == 2) {
              return "/lua" ;
         } else if (index == 3) {
              return "/web" ;
         } else if (index == 4) {
              return "/script" ;
         }
          return null ;
    }

    public String getReceiveFileUrl() {
        return receiveFileUrl;
    }

    public void setReceiveFileUrl(String receiveFileUrl) {
        this.receiveFileUrl = receiveFileUrl;
    }

    public String getReceiveRoot() {
        return receiveRoot;
    }

    public void setReceiveRoot(String receiveRoot) {
        this.receiveRoot = receiveRoot;
    }

    public String getReceiveDownloadRoot() {
        return receiveDownloadRoot;
    }

    public void setReceiveDownloadRoot(String receiveDownloadRoot) {
        this.receiveDownloadRoot = receiveDownloadRoot;
    }

    public String getUploadRoot() {
        return uploadRoot;
    }

    public void setUploadRoot(String uploadRoot) {
        this.uploadRoot = uploadRoot;
    }

    public String getDownloadRoot() {
        return downloadRoot;
    }

    public void setDownloadRoot(String downloadRoot) {
        this.downloadRoot = downloadRoot;
    }

    public String getSuffixList() {
        return suffixList;
    }

    public void setSuffixList(String suffixList) {
        this.suffixList = suffixList;
    }
}