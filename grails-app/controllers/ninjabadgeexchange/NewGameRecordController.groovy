package ninjabadgeexchange

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class NewGameRecordController {

    def gamificationService

    def index = {
        def today = new Date()
        def mmddyy = "${today.month+1}-${today.date}-${today.year + 1900 - 2000}"
        def client = ConfigurationHolder.config.nbe.badgeville.siteId.replace('.com', '')

        [requestJson: params.requestJson ?:
"""
{
    "user_id":"geekeru@ninja.com",
    "params":[
        ["verb","cloud_metrics"],
        ["type","schedule"],
        ["client","${client}"],
        ["date","${mmddyy}"],
        ["frequency","daily"]
    ]
}
"""]
    }

    def postToAgent = {
        def result = gamificationService.postNewGameRecord(params.requestJson)
        flash.message = result

        redirect(action: 'index', params: [requestJson: params.requestJson])
    }
}
