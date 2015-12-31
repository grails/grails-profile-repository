<!doctype html>
<html>
    <head>
        <title><g:message code="pageNotFound" default="Page Not Found"/></title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <section id="page-content" class="error">
            <ul class="errors">
                <li><g:message code="pageNotFoundError" default="Error: Page Not Found (404)"/></li>
                <li>Path: ${request.forwardURI}</li>
            </ul>
        </section>
    </body>
</html>
