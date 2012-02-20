<html>
    <head>
        <title>New Game Record</title>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="body">
            <g:form method="post" action="postToAgent">
                <h1>New Game Record</h1>
                <g:if test="${flash.message}">
                    <div class="message">Game Agent response: ${flash.message}</div>
                </g:if>
                <table>
                    <tr>
                        <td><label>Request JSON:</label></td>
                    </tr>
                    <tr>
                        <td><textarea name="requestJson" rows="12" cols="40">${requestJson}</textarea></td>
                    </tr>
                </table>
                <div class="buttons">
                    <span class="button"><g:submitButton name="postToAgent" class="save" value="Post to Game Agent"/></span>
                </div>
                <div class="buttons">
                    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
