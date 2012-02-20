package ninjabadgeexchange

import java.util.Date

class BadgevillePost {

    String userId
    String gameRecordId
    String badgevilleResponse
    Date dateCreated

    static constraints = {
        userId blank: false
        gameRecordId blank: false
        badgevilleResponse blank: false, maxSize: 1024*2
    }

    static mapping = {
        userId index: true
        sort dateCreated: 'desc'
    }

    def beforeUpdate() {
        throw new RuntimeException('Update not allowed')
    }
}
