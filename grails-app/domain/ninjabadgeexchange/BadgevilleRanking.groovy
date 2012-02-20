package ninjabadgeexchange

import java.util.Date

class BadgevilleRanking {

    String userId
    int rank
    Date lastUpdated

    static constraints = {
        userId blank: false
    }

    static mapping = {
        userId index: true
        rank index: true
        sort rank: 'desc'
    }

    static bumpRanking(userId) {
        def userRecord = BadgevilleRanking.findByUserId(userId)
        if (!userRecord)
            userRecord = new BadgevilleRanking(userId: userId, rank: 1)
        else
            userRecord.rank ++

        userRecord.save()
    }
}
