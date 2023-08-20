<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name">
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button type="submit" class="btn btn-primary mb-2""><#if isRegisterForm>Create<#else>Sign in</#if></button> </br>
        <#if !isRegisterForm><a href = "registration">No account?</a></#if>
</form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>