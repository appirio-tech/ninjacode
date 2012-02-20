package ninjabadgeexchange

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class BadgeSyncJob {
    def timeout = ConfigurationHolder.config.nbe.badgesync.pollIntervalSeconds * 1000
    def concurrent = false

    def badgeSyncService

    def execute() {
        badgeSyncService.syncBadges()
    }
}
