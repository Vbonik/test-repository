<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sj:head jqueryui="true"/>




<s:url id="echo" value="/fileList?currentDirectory.typeOfFile=2"/>
<sjt:tree 
    id="tree"
    jstreetheme="apple"
    rootNode="currentDirectory"
    childCollectionProperty="fileList"
    nodeIdProperty="absolutePath"
    nodeTitleProperty="name"
    nodeHref="%{echo}"
    nodeHrefParamName="currentDirectory.absolutePath"
    openAllOnLoad="true"
    nodeTargets="result"
     >
</sjt:tree>

<strong>Files :</strong>

<div id="result" class="result ui-widget-content ui-corner-all">  </div>


