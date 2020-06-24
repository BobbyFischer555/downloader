<#import "parts/common.ftl" as c>
<@c.page>
    <#if success??>
        <p class="text-success">${success}</p>
    </#if>
    <#if error??>
        <p class="text-danger">${error}</p>
    </#if>
    <form action="/searchResult" method="post">
        <label for="videoSearch" class="cols-sm-2 control-label" >
            <p class="text-primary">Video Search</p>
        </label>
        <input type="text" name="uniqueId" id="videoSearch" placeholder="Enter  Video Id">
        <button type="submit" class="btn btn-primary">Search</button>
        <input type="hidden" name="type" value="video">
    </form>


</@c.page>