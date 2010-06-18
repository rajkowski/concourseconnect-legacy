<%--
  ~ ConcourseConnect
  ~ Copyright 2009 Concursive Corporation
  ~ http://www.concursive.com
  ~
  ~ This file is part of ConcourseConnect, an open source social business
  ~ software and community platform.
  ~
  ~ Concursive ConcourseConnect is free software: you can redistribute it and/or
  ~ modify it under the terms of the GNU Affero General Public License as published
  ~ by the Free Software Foundation, version 3 of the License.
  ~
  ~ Under the terms of the GNU Affero General Public License you must release the
  ~ complete source code for any application that uses any part of ConcourseConnect
  ~ (system header files and libraries used by the operating system are excluded).
  ~ These terms must be included in any work that has ConcourseConnect components.
  ~ If you are developing and distributing open source applications under the
  ~ GNU Affero General Public License, then you are free to use ConcourseConnect
  ~ under the GNU Affero General Public License.
  ~
  ~ If you are deploying a web site in which users interact with any portion of
  ~ ConcourseConnect over a network, the complete source code changes must be made
  ~ available.  For example, include a link to the source archive directly from
  ~ your web site.
  ~
  ~ For OEMs, ISVs, SIs and VARs who distribute ConcourseConnect with their
  ~ products, and do not license and distribute their source code under the GNU
  ~ Affero General Public License, Concursive provides a flexible commercial
  ~ license.
  ~
  ~ To anyone in doubt, we recommend the commercial license. Our commercial license
  ~ is competitively priced and will eliminate any confusion about how
  ~ ConcourseConnect can be used and distributed.
  ~
  ~ ConcourseConnect is distributed in the hope that it will be useful, but WITHOUT
  ~ ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  ~ FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more
  ~ details.
  ~
  ~ You should have received a copy of the GNU Affero General Public License
  ~ along with ConcourseConnect.  If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ Attribution Notice: ConcourseConnect is an Original Work of software created
  ~ by Concursive Corporation
  --%>
<%@ taglib uri="/WEB-INF/portlet.tld" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/concourseconnect-taglib.tld" prefix="ccp" %>
<%@ page import="com.concursive.commons.text.StringUtils"%>
<jsp:useBean id="title" class="java.lang.String" scope="request"/>
<jsp:useBean id="wikiHtml" class="java.lang.String" scope="request"/>
<jsp:useBean id="contactUs" class="com.concursive.connect.web.modules.contactus.dao.ContactUsBean" scope="request"/>
<portlet:defineObjects/>
<%@ include file="../../initPage.jsp" %>
<script language="JavaScript" type="text/javascript">
  function checkForm(form) {
    var formTest = true;
    var messageText = "";
    // verify any form field validations and requirements

    if (!formTest) {
      messageText = "The message could not be submitted.          \r\nPlease verify the following items:\r\n\r\n" + messageText;
      alert(messageText);
      return false;
    } else {
      return true;
    }
  }
</script>
<div class="portletWrapper">
  <c:if test="${!empty title}">
    <h3><c:out value="${title}"/></h3>
  </c:if>
  <c:if test="${!empty introductionMessage}">
    <p><c:out value="${introductionMessage}"/></p>
  </c:if>
  <c:if test='<%= contactUs.getError("actionError") != null %>'>
    <div class="portlet-message-error">
      <c:out value='<%= contactUs.getError("actionError") %>'/>
    </div>
  </c:if>
  <div class="formContainer">
    <portlet:actionURL var="saveFormUrl">
      <portlet:param name="portlet-command" value="save"/>
    </portlet:actionURL>
    <form method="POST" name="inputForm" action="${saveFormUrl}" onSubmit="return checkForm(this);">
      <%= wikiHtml %>
      <c:if test="${'true' eq param.popup || 'true' eq popup}">
        <input type="hidden" name="popup" value="true"/>
      </c:if>
      <input type="submit" class="submit" value="Get Information" />
      <c:if test="${'true' eq param.popup || 'true' eq popup}">
        <input type="button" value="Cancel" class="cancel" id="panelCloseButton">
      </c:if>
      <img src="${ctx}/images/loading16.gif" alt="loading please wait" class="submitSpinner" style="display:none"/>
    </form>
  </div>
</div>
