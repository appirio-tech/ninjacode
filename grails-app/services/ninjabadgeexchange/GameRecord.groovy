package ninjabadgeexchange

class GameRecord {
    def userId
    def metadata
    def id

    String toString() {
        "${userId} ${metadata} ${id}"
    }
}
