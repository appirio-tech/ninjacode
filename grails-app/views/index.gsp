<html>
    <head>
        <title>Ninja Badge Exchange</title>

        <script src="http://js.pusherapp.com/1.9/pusher.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            var pusher = new Pusher('${org.codehaus.groovy.grails.commons.ConfigurationHolder.config.pusherapp.applicationKey}');
            var channel = pusher.subscribe('${org.codehaus.groovy.grails.commons.ConfigurationHolder.config.pusherapp.alertsChannel}');
            channel.bind('${org.codehaus.groovy.grails.commons.ConfigurationHolder.config.pusherapp.activityEvent}', function(event) {
                var html =
                    '<b>Latest activity</b>: <i>' + event.userId + '</i> delivered <i>' +
                    event.gameRecordId + '</i> to <i>Badgeville</i> with response:<br/><br/>' +
                    event.badgevilleResult +
                    '<br/><br/><font size="-2">' + new Date() + '</font>';

                document.getElementById('alerts').innerHTML = html;
            });
        </script>

        <meta name="layout" content="main" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <!--
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>Application Status</h1>
                    <ul>
                        <li>App version: <g:meta name="app.version"></g:meta></li>
                        <li>Grails version: <g:meta name="app.grails.version"></g:meta></li>
                        <li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
                        <li>JVM version: ${System.getProperty('java.version')}</li>
                        <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                        <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                        <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                        <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                    </ul>
                    <h1>Installed Plugins</h1>
                    <ul>
                        <g:set var="pluginManager"
                               value="${applicationContext.getBean('pluginManager')}"></g:set>

                        <g:each var="plugin" in="${pluginManager.allPlugins}">
                            <li>${plugin.name} - ${plugin.version}</li>
                        </g:each>

                    </ul>
                </div>
                <div class="panelBtm"></div>
            </div>
        </div>
        -->
        <div id="pageBody">
            <h1>Enter the Ninja Badge Exchange, Worthy One</h1>

            <div id="controllerList" class="dialog">
                <h2>Available Gates:</h2>
                <ul>
                    <li class="controller"><g:link controller="badgevillePost">Badgeville Posts</g:link></li>
                    <li class="controller"><g:link controller="badgevilleRanking">Badgeville Rankings</g:link></li>
                    <li class="controller"><g:link controller="newGameRecord">New Game Record</g:link></li>
                </ul>
            </div>
            <br/>
            <br/>
            <div id="alerts"></div>
            <p>Refresh rate: ${org.codehaus.groovy.grails.commons.ConfigurationHolder.config.nbe.badgesync.pollIntervalSeconds / 60d} minutes</p>
        </div>
    </body>
</html>
