<%@ page pageEncoding="utf-8" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>增值税计算系统</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/static/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <link rel="stylesheet"  href="<%=request.getContextPath()%>/assets/css/bootstrap.css"  />
 <link rel="stylesheet"  href="<%=request.getContextPath()%>/assets/css/font-awesome.css" />
 <link rel="stylesheet"  href="<%=request.getContextPath()%>/js/alert/jquery-confirm.min.css" >
 
<script  type="text/javascript"  src="<%=request.getContextPath()%>/assets/js/jquery-1.10.2.js"></script>
<script  type="text/javascript"  src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/alert/jquery-confirm.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js" ></script>

<style type="text/css">

body{
	font-size:100%;
    background: #dddddd;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

.form {
	background: rgb(255, 255, 255, 0.9);
	width: 400px;
	margin: 100px auto;
}

#login_form {
	display: block;
}

.fa {
	display: inline-block;
	top: 27px;
	left: 6px;
	position: relative;
	color: #ccc;
}

input[type="text"], input[type="password"] {
	padding-left: 26px;
}

.checkbox {
	padding-left: 21px;
}
</style>

</head>

<body>
<div class="container">
		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form" method="post">
				<h3 class="form-title">用户登录</h3>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i> <input
							class="form-control required" type="text" placeholder="请输入账号"
							name="username" autofocus="autofocus" maxlength="20" />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"></i> <input
							class="form-control required" type="password" 
							placeholder="请输入密码" name="password" maxlength="8" />
					</div>
					<div class="form-group">
						<label class="checkbox"> 
							<input type="checkbox"  name="rememberMe" value="1" /> 记住用户名
						</label>
						<hr />
						<a href="<%=request.getContextPath()%>/vat/userReg.do" id="register_btn" class="">免费注册</a>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-success pull-right" value="登录 " />
					</div>
				</div>
			</form>
		</div>
		
		<div class="row">
					<span style="margin-left:500px"><span>
					<img src="<%=request.getContextPath()%>/user/getWebAddrQrCode.do"  height=“120” width="120"/>
	    </div> 
	</div>
		
	</div>
	
	<script>
	
	 $(document).ready(function() {
			/**登录验证**/
			$("#login_form").validate({
				rules: {
					username: "required",
					password: {
						required: true,
						minlength: 5
					},
				},
				messages: {
					username: "请输入账号",
					password: {
						required: "请输入密码",
						minlength: jQuery.format("密码不能小于{0}个字 符")
					},
				},
				submitHandler:function(form){
		            $.ajax({
		        		dataType : "json",
		        		url : "<%=request.getContextPath()%>/user/login.do",  
		        		type : "post", 
		        		data : $("#login_form").serialize(), 
		        		success : function(data) {
		        			$.alert(data.message);
		        			if(data.success){
		        				window.location.href = '<%=request.getContextPath()%>/vat/index.do';
		        			}
		        		},
		        		error : function (e){
		        			var d = e.responseJSON;
		        			if(d){
		        				$.alert(d.message);
		        			}
		        		}
		        	});
		            return false; //阻止form提交
		        }
			});
	 });
	
	</script>
	
	
	
</body>
</html>