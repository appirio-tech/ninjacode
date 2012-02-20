package ninjabadgeexchange

import grails.converters.JSON

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class AlertService {

    def pusherService

    def alertActivity(userId, gameRecordId, badgevilleResult) {
        def event = [:]
        event.userId = userId
        event.gameRecordId = gameRecordId
        event.badgevilleResult = badgevilleResult
        def message = "${event as JSON}"

        pusherService.triggerPush(
            ConfigurationHolder.config.pusherapp.alertsChannel,
            ConfigurationHolder.config.pusherapp.activityEvent,
            message)
    }
}
