<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>

        <g:layoutHead/>
    </head>
    <body>
        <header id="header" role="banner">
            <nav class="container" role="navigation">
                <ul>
                    <li id="logo" role="banner" style="float:left;"><a href="/#"><asset:image src="grails-cupsonly-logo-white.svg" alt="Home Page" title="Home Page"/></a></li>
                    <li><a href="/#"><g:message code="home" default="Home"/></a></li>
                    <li><a href="/#controllers"><g:message code="controllers" default="Controllers"/></a></li>
                </ul>
            </nav>
        </header>

        <div id="skip-to-content" class="hidden">
            <a href="#page-body"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        </div>

        <g:layoutBody/>

        <div id="spinner" class="spinner hidden"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

        <footer id="footer" role="contentinfo">
            <p>
                    <g:message code="poweredBy" default="Powered by"/> &nbsp;
                    <a href="http://grails.org"><g:message code="grails" default="Grails"/></a>
            </p>
        </footer>
    </body>
</html>
