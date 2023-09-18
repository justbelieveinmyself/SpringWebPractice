
<#include "security.ftlh"/>
<#import "pager.ftlh" as p>
<@p.pager url page>

<div class="row row-cols-1 row-cols-md-3 g-4" id="message-list">
    <#list page.getContent() as message>
<div class="col" data-id="${message.id}" style="width: 400px; height: auto;">
    <div class="card h-100">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top" alt="Image"/>
        </#if>
        <div class="card-body">
            <h5 class="card-title">
                <a href="/user-messages/${message.author.id}" style="color: black; text-decoration: none;">${message.authorName}</a>
                <br> <span>#${message.tag}</span> </br>
            </h5>
            <p class="card-text">${message.text}</p>
        </div>
        <div class="card-footer">
            <small class="text-body-secondary">Last update: ${message.getFormattedTime()}</small>
            <#if currentUserId == message.author.id>
            <a class="btn btn-info "  href="/user-messages/${message.author.id}?message=${message.id}">
                Edit
            </a>
        </#if>
        </div>
    </div>
</div>
<#else> <br> No messages </br>
</#list>
</div>

</@p.pager>