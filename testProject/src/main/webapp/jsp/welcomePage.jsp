
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<sj:head jqueryui="true"/>
        <sjt:tree id="treeStatic" jstreetheme="default" openAllOnLoad="true">
                
                        <sjt:treeItem title="AJAX Links">
                                        <s:url id="ajax1" value="/fileList"/>
                                <sjt:treeItem title="Ajax 1" href="%{ajax1}" targets="result"/>
                                        <s:url id="ajax2" value="/fileList"/>
                                <sjt:treeItem title="Ajax 2" href="%{ajax2}" targets="result" effect="highlight" effectDuration="2500"/>
                                        <s:url id="ajax3" value="/fileList"/>
                                <sjt:treeItem title="Ajax 3" href="%{ajax3}" targets="result" onBeforeTopics="beforeLink" onCompleteTopics="completeLink"/>
                                        <s:url id="ajax4" value="/fileList"/>
                                <sjt:treeItem title="Ajax 4" href="%{ajax4}" targets="result" effect="bounce" effectDuration="1000"/>
                        </sjt:treeItem>
              
        </sjt:tree>
  
  <strong>Result Div :</strong>
  <div id="result" class="result ui-widget-content ui-corner-all">Click on the AJAX Links above.</div>

