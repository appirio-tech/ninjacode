package ninjabadgeexchange

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BadgevilleService {

    def auditTrailService
    def alertService

    def postGameRecords(gameRecords) {
        def postedIds = []

        gameRecords.each { gameRecord ->
            def result = postActivity(gameRecord.userId, gameRecord.metadata)
            if (result) {
                logSuccessfulResponse(gameRecord.userId, gameRecord.id, result)
                postedIds << gameRecord.id
            }
        }

        postedIds
    }

    private postActivity(userId, metadata) {
        def result
        def body = "site=${ConfigurationHolder.config.nbe.badgeville.siteId}&user=${userId}&${metadata}"

        try {
            withRest(uri: ConfigurationHolder.config.nbe.badgeville.activities.endpoint) {
                def response = post(body: body,
                                    requestContentType: groovyx.net.http.ContentType.URLENC, // send this
                                    contentType: groovyx.net.http.ContentType.JSON) // receive this
                if (response.status == 201)
                    result = "${response.data}"
                else
                    logUnexpectedResponse(response)
            }
        } catch (ex) {
            logUnexpectedResponse(ex.response)
        }

        result
    }

    private logSuccessfulResponse(userId, gameRecordId, result) {
        log.debug "Badgeville response: ${result}"

        auditTrailService.logBadgevillePost(userId, gameRecordId, result)

        alertService.alertActivity(userId, gameRecordId, result)
    }

    private logUnexpectedResponse(response) {
        log.error "Unexpected status: ${response.statusLine}"
    }
}
