<#include "security.ftlh"/>
<#import "pager.ftlh" as p>
<@p.pager url page />


<div class="row row-cols-3 g-3" id="message-list">
    <#list page.getContent() as message>
    <div class="card py-2 mx-2" data-id="${message.id}" style="width: 18rem;height: auto;">
        <#if message.filename??>
            <img src="/img/${message.filename}" class="card-img-top"/>
        </#if>
        <div class="m-2">
            <br> <span>#${message.tag}</span> </br>
            <strong>${message.text}</strong>
        </div>
        <div class="card-footer text-body-secondary">
            <a href="/user-messages/${message.author.id}">${message.authorName}</a>
            <#if currentUserId == message.author.id>
                <a class="btn btn-info" href="/user-messages/${message.author.id}?message=${message.id}">
                    Edit
                </a>
            </#if>
        </div>
    </div>
    <#else> <br> No messages </br>
    </#list>
</div>
<@p.pager url page/>