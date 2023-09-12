<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <div>You're authorized! Hello, <strong> ${name} </strong></div>
    <div>You're id: <strong>${currentUserId}</strong></div>
    <div>Administrator: <strong><#if isAdmin>yes<#else>no</#if> </strong></div>
</@c.page>

