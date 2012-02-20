package ninjabadgeexchange

class AuditTrailService {

    static transactional = true

    def logBadgevillePost(userId, gameRecordId, badgevilleResponse) {
        new BadgevillePost(userId: userId, gameRecordId: gameRecordId, badgevilleResponse: badgevilleResponse).save()

        BadgevilleRanking.bumpRanking(userId)
    }
}
