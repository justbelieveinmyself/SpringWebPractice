<#macro pager url page>
    <div>
        <#nested>
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link">First</a>
            </li>
            <#list 1..page.getTotalPages() as p>
            <#if p == page.getNumber()+1>
            <li class="page-item active"><a class="page-link" href="?page=${p-1}">${p}</a></li>
            <#else>
            <li class="page-item"><a class="page-link" aria-current="page" href="?page=${p-1}">${p}</a></li>
            </#if>
            </#list>
            <li class="page-item">
                <a class="page-link" href="#">Last</a>
            </li>
        </ul>
    </div>
</#macro>