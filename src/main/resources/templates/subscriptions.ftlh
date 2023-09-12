<#import "parts/common.ftlh" as c>
<@c.page>
    <strong>${userChannel.username}</strong>
    <div>${type}</div>
    <ul class="list-group">
        <#list users as user>
            <li class="list-group-item">
                <a href="/user-messages/${user.id}">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>