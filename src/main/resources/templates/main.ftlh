<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
    <@l.logout/>
</div>
    <div>
        <b>Write new message:</b>
        <form method="post" action="/main">
            <input type="text" name="tag" placeholder="Tag">
            <input type="text" name="text" placeholder="Message">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <b>Tag filter</b>
    <form method="get" action="/main">
        <input type="text" name="filter">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Search</button> </br>
    <b>Messages (author, tag, name):</b> </br>
        <#list messages as message>
            <div>
                <b>${message.authorName}</b>
                <span>${message.tag}</span>
                <span>${message.text}</span>
            </div>
        <#else> No messages </br>
        </#list>
    <a href = "main">Main</a> </br>
    <a href="/">Home</a> </br>
    <a href = "test">Test</a> </br>
    <a href = "login">Log in</a> </br>
    <a href = "registration">Sign up</a> </br>
</@c.page>