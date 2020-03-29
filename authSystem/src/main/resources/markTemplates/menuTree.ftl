
    <ul class="layui-nav layui-nav-tree" style="width: auto" id="L_demoNav" lay-filter="test">
        <#list permissionList as p>
         <li class="layui-nav-item layui-nav-itemed">
             <a href="${p.url}">${p.name}<span class="layui-nav-more"></span></a>
            <#if p.children??&&(p.children?size > 0)>
                <dl class="layui-nav-child">
                    <#list p.children as ch>
                        <dd><a href="${ch.url}">${ch.name}</a></dd>
                    </#list>
                </dl>
            </#if>
         </li>
        </#list>
        <#if permissionList??&&(permissionList?size = 0)>
         <li class="layui-nav-item layui-nav-itemed">
               <a href="#">暂无管理权限<span class="layui-nav-more"></span></a>
         </li>
        </#if>
    </ul>
