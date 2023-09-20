<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
    <div class="container-fluid">
        <a class="navbar-brand mx-2" href="/">Office</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Messages</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/test">Test</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
        </#if>
        <#if user?? && name != "unknown">
        <li class="nav-item">
            <a class="nav-link" href="/user/profile">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/user-messages/${currentUserId}">My Messages</a>
        </li>
    </#if>
    </ul>
    <div class="navbar-text me-3"><strong>${name}<#if isAdmin>😎</#if></strong></div>
    <#if name != "unknown">
    <div class="me-2"><@l.logout/> </div>
</#if>
</div>
</div>
</nav>