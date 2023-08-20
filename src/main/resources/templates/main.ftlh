<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="d-flex align-items-center">
        <form method="get" action="/main" class="d-flex">
            <input type="text" name="filter" class="form-control me-2" value="${filter!}" placeholder="Search by tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
    <a class="btn btn-primary my-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Write new message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="mb-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <input type="text" class="form-control" name="text" placeholder="Message">
                <input type="text" class="form-control" name="tag" placeholder="Tag">
                <div class="input-group">
                    <input type="file" class="form-control" name="file" aria-describedby="inputGroupFileAddon04">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
    <div class="row row-cols-3 g-3">
        <#list messages as message>
            <div class="card py-2 mx-2" style="width: 18rem;height: auto;">
                <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${message.tag}</span>
                    <strong>${message.text}</strong>
                </div>
                <div class="card-footer text-body-secondary">
                    ${message.authorName}
                </div>
            </div>
            <#else> No messages </br>
        </#list>
    </div>

</@c.page>