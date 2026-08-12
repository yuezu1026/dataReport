<!--
  Copyright 2026 yuezu1026

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<%@ page pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>增值税计算系统</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/static/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap.css"  />
<script  type="text/javascript"  src="<%=request.getContextPath()%>/assets/js/jquery-1.10.2.js"></script>
<script  type="text/javascript"  src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<style>
        body{
            background-color: #C7EDCC;
            font-family: "microsoft yahei";
            /*min-width: 800px;*/
        }
        img{
            width: 100%;
        }
        #myppt{
            margin-top: -20px;
        }
    </style>
</head>
<body>
	 <div class="col-lg-8 col-lg-offset-2" id="login" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-title">
                    <h1 class="text-center">系统管理员登录</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" action="<%=request.getContextPath()%>/system/login.do" method="post">
                            <div class="form-group">
                                <label for="">用户名</label>
                                <input class="form-control" type="text"  name="username" placeholder="请输入管理员账号">
                            </div>
                            <div class="form-group">
                                <label for="">密码</label>
                                <input class="form-control" type="password" name="password"  placeholder="请输入管理员密码">
                            </div>
                            <div class="text-right">
                                <button class="btn btn-primary" type="submit">登录</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>