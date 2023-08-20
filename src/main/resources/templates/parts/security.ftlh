<#assign
    known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        securityUser = SPRING_SECURITY_CONTEXT.authentication.principal
        user = securityUser.getUser()
        name = user.getName()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
    >
</#if>