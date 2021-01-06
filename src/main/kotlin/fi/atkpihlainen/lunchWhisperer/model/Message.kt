package fi.atkpihlainen.lunchWhisperer.model

data class Message(
    var menu: Menu
) {
    override fun toString(): String {
        return "Message(menu=$menu)"
    }
}
