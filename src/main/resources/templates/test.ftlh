<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>You're authorized! </div>
<div>
    <@l.logout/>
</div>
    <a href = "main">Main</a> </br>
    <a href="/">Home</a> </br>
    <a href = "test">Test</a> </br>
    <a href = "login">Log in</a> </br>
    <a href = "registration">Sign up</a> </br>
</@c.page>

