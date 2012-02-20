package ninjabadgeexchange

class BadgeSyncService {

    def gamificationService
    def badgevilleService

    def syncBadges() {
        log.debug 'syncBadges'

        def newGameRecords = gamificationService.getNewGameRecords()
        def postedIds = badgevilleService.postGameRecords(newGameRecords)
        gamificationService.markSynced(postedIds)
    }
}
