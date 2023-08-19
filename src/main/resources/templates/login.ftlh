<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <b>Log in</b>
    <@l.login "/login" />
    <a href = "registration">Sign up</a>
</@c.page>