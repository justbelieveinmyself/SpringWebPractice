<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>${username}</h5>
    <form method="post">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="Email" value="${email!''}">
            </div>
        </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary mb-2">Save</button> </br>
    </form>

</@c.page>