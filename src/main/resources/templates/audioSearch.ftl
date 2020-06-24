<#import "parts/common.ftl" as c>
<@c.page>
    <#if success??>
        <p class="text-success">${success}</p>
    </#if>
    <#if error??>
        <p class="text-danger">${error}</p>
    </#if>
    <form action="/searchResult" method="post">
        <label for="audioSearch" class="cols-sm-2 control-label" >
            <p class="text-primary">Audio Search</p>
        </label>
        <input type="text" name="uniqueId" id="audioSearch" placeholder="Enter  Audio Id">
        <button type="submit" class="btn btn-primary">Search</button>
        <input type="hidden" name="type" value="audio">
    </form>
</@c.page>