<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Welcome to Grails</title>
        <style type="text/css" media="screen">
            #status {
                background-color: #eee;
                border: .2em solid #fff;
                margin: 1em 2em 1em;
                float: left;
                -moz-box-shadow: 0px 0px 1.25em #ccc;
                -webkit-box-shadow: 0px 0px 1.25em #ccc;
                box-shadow: 0px 0px 1.25em #ccc;
                -moz-border-radius: 0.6em;
                -webkit-border-radius: 0.6em;
                border-radius: 0.6em;
            }
            #status ul {
                list-style-type: none;
            }
            #status li {
            }

            @media screen and (max-width: 480px) {
                #status {
                    display: none;
                }

        </style>
    </head>
    <body>

        <section id="page-body" class="container" role="main">

            <aside id="status" role="complementary" style="">
                <h2>Application Status</h2>
                <ul>
                    <li>Environment: ${grails.util.Environment.current.name}</li>
                    <li>App profile: ${grailsApplication.config.grails?.profile}</li>
                    <li>App version: <g:meta name="info.app.version"/></li>
                    <li>Grails version: <g:meta name="info.app.grailsVersion"/></li>
                    <li>Groovy version: ${GroovySystem.getVersion()}</li>
                    <li>JVM version: ${System.getProperty('java.version')}</li>
                    <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
                </ul>
                <h2>Artefacts</h2>
                <ul>
                    <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                    <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                    <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                    <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                </ul>
                <h2>Installed Plugins</h2>
                <ul>
                    <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                        <li>${plugin.name} - ${plugin.version}</li>
                    </g:each>
                </ul>
            </aside>

            <section id="page-content" style="">
                <h1>Welcome to Grails</h1>
                <p>Congratulations, you have successfully started your first Grails application! At the moment
                   this is the default page, feel free to modify it to either redirect to a controller or display whatever
                   content you may choose. Below is a list of controllers that are currently deployed in this application,
                   click on each to execute its default action:</p>

                <div id="controller-list" role="navigation">
                    <h2>Available Controllers:</h2>
                    <a id="controllers"></a>
                    <ul>
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                        </g:each>
                    </ul>
                </div>
            </section>

        </section>

    </body>
</html>
