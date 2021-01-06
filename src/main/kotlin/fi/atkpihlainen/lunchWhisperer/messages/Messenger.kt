package fi.atkpihlainen.lunchWhisperer.messages

interface Messenger {
    fun sendMenus(message: String)
}
