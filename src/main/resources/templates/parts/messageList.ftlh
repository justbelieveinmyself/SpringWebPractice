
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
        <div class="card-footer container">
            <div class="row align-items-center" style="height: 80px">
                <small class="text-body-secondary col-5 align-self-center pb-3"><br> Last update: </br> ${message.getFormattedTime()}</small>
            <a href="/messages/${message.id}/like" style="text-decoration: none; color: red;" class="col align-self-center">
                ${message.likes}
            <#if message.meLiked>
                <i class="fa-solid fa-heart fa-2xl"></i>
            <#else>
                <i class="fa-regular fa-heart fa-2xl"></i>
            </#if>
                </a>
            <#if currentUserId == message.author.id>
                <div class="row align-items-center col">
            <a class="btn btn-info col mb-1 "  href="/user-messages/${message.author.id}?message=${message.id}">
                Edit
            </a>
            <a class="btn btn-info col"  href="/messages/${message.id}/delete">
                Delete
            </a>
                </div>
        </#if>
            </div>
            </div>
    </div>
</div>
<#else> <br> No messages </br>
</#list>
</div>

</@p.pager>