<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>黄浦区教育学院办公管理系统</title>
         <script src="js/login.js" type="text/javascript"></script>
        <link href="/oa/css/login-box.css" rel="stylesheet" type="text/css"/>
    </head>
   
   
    <body style="margin: 0;" onload="remCookie();" >
    <img src="/oa/images/bk.jpg" id="bgimage"></img>
        <div id="apDiv1">
            <form id="form1" action="signinservlet"  name="form1" method="post" onsubmit="validation()">
               ​
                <div id="login-box">
                    <h2 style="padding-bottom:30px;">黄浦区教育学院办公管理系统</h2>
                       
                     
                    <div id="login-box-name">用户名:</div>
                    <div id="login-box-field">
                        <input id="userid" name="userid" class="form-login" title="Username" value="" size="30"
                               maxlength="2048"/>
                        <input name="action" value="signin" type="hidden"/>
 
                    </div>
                    <div id="login-box-name">密码:</div>
                    <div id="login-box-field">
                        <input id="password" name="password" type="password" class="form-login" title="Password"
                               value="" size="30" onkeydown="javascript:fnTrapKD(event)" />
                    </div>
                    <br/><span style='color:red;font-size:15px;' id="usernamespan"> </span><span id="passwordspan"></span>  <br/>                   
                     
                    <span class="login-box-options" >
                       <a style="margin-left:10px;" href="#" onclick="submitform();">
                            <img src="/oa/images/LoginButton.png" width="80" height="20"/>
                       </a>
                       <span style="float:right;margin-right:40px">
                        <input id="rememberMe" type="checkbox" name="rememberMe"></input> 记住用户名
                        <a href="#" style="margin-left:20px;">忘记密码？</a>
                        </span>
                    </span>
                    <p style="color:Gray; font-size:xx-small;"> 建议使用Google Chrome浏览器（1-20+版本）以达到最佳使用效果，本系统也支持微软IE（7-10）、Firefox(2-13+)、Safari(3.1.2,4,5)、Safari(iOS)4.3.3,5 浏览器 </p>
                </div>
            </form>
        </div>
    </body>
</html>