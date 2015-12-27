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
        <header id="header" class="" role="banner">
            <nav class="container" role="navigation">
                <ul>
                    <li id="logo" role="banner" style="align:left;"><a href="http://grails.org"><asset:image src="grails-cupsonly-logo-white.svg" alt="Grails" title="Grails"/></a></li>
                    <li><a href="/#">Home</a></li>
                    <li><a href="/#controllers">Controllers</a></li>
                </ul>
            </nav>
        </header>

        <div id="skip-to-content" class="hidden">
            <a href="#page-body"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        </div>

        <g:layoutBody/>

        <div id="spinner" class="spinner hidden"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

        <footer id="footer" class="footer" role="contentinfo">
            <p>
            <g:message code="poweredby" default="Powered by"/> &nbsp;
            <a href="http://grails.org"><g:message code="grails" default="Grails"/></a>
            </p>
        </footer>
    </body>
</html>
