<#import "parts/common.ftlh" as c>

<@c.page>
    <strong>${userChannel.username}</strong> </br>
    <#if !isCurrentUser>
        <#if isSubscriber>
            <a class="btn btn-secondary" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
        <#else>
            <a class="btn btn-warning"  href="/user/subscribe/${userChannel.id}">Subscribe</a>
        </#if>
    </#if>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">
                            <a href="/user/subscriptions/${userChannel.id}/list">Subscriptions</a>
                        </div>
                        <div class="card-text">${subscriptionsCount}</div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">
                            <a href="/user/subscribers/${userChannel.id}/list">Subscribers</a>
                        </div>
                        <div class="card-text">${subscribersCount}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <#include "parts/messageEdit.ftlh"/>
    </#if>

    <#include "parts/messageList.ftlh"/>
</@c.page>