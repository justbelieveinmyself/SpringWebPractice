<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    ${message!" " }

    <@l.login "/login" false/>
<#if SPRING_SECURITY_LAST_EXCEPTION??>
<div class="alert alert-primary" role="alert">
    ${SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
</#if>




</@c.page>