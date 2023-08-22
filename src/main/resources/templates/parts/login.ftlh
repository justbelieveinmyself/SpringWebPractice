<#macro login path isRegisterForm>
<form action="${path}" method="post" >
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Username:</label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="Username" value="<#if user??>${user.username}</#if>">

            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="Email" value="<#if user??>${user.email}</#if>">
                <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
                </#if>
            </div>
            </div>
        </#if>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="Password">
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid', '')}" placeholder="Password">
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="g-recaptcha" data-sitekey="6LfAwscnAAAAAFWMQwWo0TiAICug7xAf1act_YZj"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        <#else>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me!
                </label>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary mb-2"><#if isRegisterForm>Create<#else>Sign in</#if></button> </br>
        <#if !isRegisterForm><a href = "registration">No account?</a></#if>
</form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>