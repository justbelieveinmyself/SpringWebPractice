<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="d-flex align-items-center">
        <form method="get" action="/main" class="d-flex">
            <input type="text" name="filter" class="form-control me-2" value="${filter!}" placeholder="Search by tag"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>

    <#include "parts/messageEdit.ftlh"/>
    <#include "parts/messageList.ftlh"/>

</@c.page>