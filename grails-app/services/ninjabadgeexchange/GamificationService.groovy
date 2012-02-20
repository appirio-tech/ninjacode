package ninjabadgeexchange

import grails.web.JSONBuilder

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class GamificationService {

    static final GAME_AGENT_PATH = 'services/apexrest/GameAgent'

    def getNewGameRecords() {
        def gameRecords = []

        withRest(uri: ConfigurationHolder.config.nbe.gameagent.url) {
            headers.'Authorization' = "OAuth ${ConfigurationHolder.config.nbe.gameagent.accessToken}"

            def json = get(path: GAME_AGENT_PATH) // gets 'Generated' status by default

            json.data.each { userRecord ->
                def userId = userRecord.key

                userRecord.value.each { gameInfo ->
                    def gameRecord = new GameRecord()

                    gameRecord.userId = userId
                    gameRecord.metadata = gameInfo.Metadata
                    gameRecord.id = gameInfo.ID

                    gameRecords << gameRecord
                }
            }
        }

        gameRecords
    }

    def markSynced(postedIds) {
        def updatedCount = 0

        if (postedIds) {
            def builder = new JSONBuilder()
            def json = builder.build {
                id_list = postedIds
            }
            def idList = json.toString()

            withRest(uri: ConfigurationHolder.config.nbe.gameagent.url) {
                headers.'Authorization' = "OAuth ${ConfigurationHolder.config.nbe.gameagent.accessToken}"
                headers.Accept = 'application/json' // needed b/c Game Agent sets rsp content type as JSON (!!)

                def response = put(path: GAME_AGENT_PATH, // sets status to 'Synced' by default
                                   query: [t: 'ID'], body: idList,
                                   requestContentType: groovyx.net.http.ContentType.JSON, // we're sending JSON
                                   contentType: groovyx.net.http.ContentType.TEXT) // rsp actually text, not JSON
                updatedCount = "${response.data}" as int
            }
            log.debug "Sync'ed ${updatedCount} records"
        }

        updatedCount
    }

    def postNewGameRecord(requestJson) {
        def result

        withRest(uri: ConfigurationHolder.config.nbe.gameagent.url) {
            headers.'Authorization' = "OAuth ${ConfigurationHolder.config.nbe.gameagent.accessToken}"
            headers.Accept = 'application/json' // needed b/c Game Agent sets rsp content type as JSON (!!)

            def response = post(path: GAME_AGENT_PATH,
                                body: requestJson,
                                requestContentType: groovyx.net.http.ContentType.JSON, // we're sending JSON
                                contentType: groovyx.net.http.ContentType.TEXT) // rsp actually text, not JSON
            result = "${response.data}" as String
        }

        result
    }

}
