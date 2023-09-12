
<a class="btn btn-primary my-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Message editor
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="mb-3">
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="_csrf" value="${_csrf.token}"/>
            <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}" value="<#if message??>${message.text}</#if>"
                   name="text"  placeholder="Message"/>
            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
            <input type="text" class="form-control ${(tagError??)?string('is-invalid', '')}" value="<#if message??>${message.tag}</#if>"
               name="tag" placeholder="Tag"/>
            <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
            <div class="input-group">
                <input type="file" class="form-control" name="file" aria-describedby="inputGroupFileAddon04"/>
            </div>
            <input type="hidden" name="id" value="<#if message?? && message.id??>${message.id}</#if>"/>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>
