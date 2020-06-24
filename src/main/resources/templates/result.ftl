<#import "parts/common.ftl" as c>
<@c.page>


    <#if details??>
        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th scope="col">Author</th>
                <th scope="col">Title</th>
                <th scope="col">Length</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>${details.author}</td>
                <td>${details.title}</td>
                <td>${details.lengt}</td>
            </tr>

            </tbody>
        </table>
    </#if>



    <#if videoFormats?? && details??>

        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th scope="col">Extension</th>
                <th scope="col">Quality</th>
                <th scope="col">Resolution</th>
                <th scope="col">Bitrate</th>
            </tr>
            </thead>
            <tbody>
            <#list videoFormats as format>
                <tr>
                    <td>${format.extension}</td>
                    <td>${format.quality}</td>
                    <td>${format.resolution}</td>
                    <td>${format.bitrate}</td>
                    <td>
                        <form action="/downloadVideo" method="post">
                            <button type="submit" class="btn btn-primary btn-block" value="${details.id}" name="id">
                                Download
                            </button>
                            <input type="hidden" name="listIndex" value="${format.listIndex}">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>



    <#if audioFormats?? && details??>

        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th scope="col">Extension</th>
                <th scope="col">Quality</th>
                <th scope="col">Bitrate</th>
            </tr>
            </thead>
            <tbody>
            <#list audioFormats as format>
                <tr>
                    <td>${format.extension}</td>
                    <td>${format.quality}</td>
                    <td>${format.bitrate}</td>
                    <td>
                        <form action="/downloadAudio" method="post">
                            <button type="submit" class="btn btn-primary btn-block" value="${details.id}" name="id">
                                Download
                            </button>
                            <input type="hidden" name="listIndex" value="${format.listIndex}">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>


    <#if error??>
        <p class="text-danger">${error}</p>
    </#if>
    <#if empty??>
        <p class="text-info">${empty}</p>
    </#if>
</@c.page>