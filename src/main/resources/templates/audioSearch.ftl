<#import "parts/common.ftl" as c>
<@c.page>
    <#if success??>
        <p class="text-success">${success}</p>
    </#if>
    <#if error??>
        <p class="text-danger">${error}</p>
    </#if>
    <form action="/searchResult" method="post">
        <input type="text" name="uniqueId">
        <button type="submit" class="btn btn-primary">Search</button>
        <input type="hidden" name="type" value="audio">
    </form>
</@c.page>