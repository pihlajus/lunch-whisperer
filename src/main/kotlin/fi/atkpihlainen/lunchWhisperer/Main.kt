package fi.atkpihlainen.lunchWhisperer

import fi.atkpihlainen.lunchWhisperer.messages.SlackMessenger
import fi.atkpihlainen.lunchWhisperer.messages.createMessages
import fi.atkpihlainen.lunchWhisperer.utils.eduMenuExists
import fi.atkpihlainen.lunchWhisperer.utils.getEduMenu
import fi.atkpihlainen.lunchWhisperer.utils.getMenus
import fi.atkpihlainen.lunchWhisperer.utils.getWeekDay
import io.kotless.dsl.lang.event.Scheduled
import io.kotless.dsl.lang.http.Get
import org.jsoup.Jsoup

const val LOUNAAT_INFO_URL = "https://www.lounaat.info/33950-pirkkala"
const val EDU_URL = "https://www.edunherkkukeidas.fi/toimipiste/pirkkala/"
const val SLACK_OAUTH_TOKEN = "<your_slack_oauth_token>"
const val SLACK_CHANNEL_ID = "<your_slack_channel_id>"
const val EDU_NAME = "Edun Herkkukeidas Pirkkala"
val MENUS_TO_SENT = listOf("Ninan Keittiö Veska", EDU_NAME) // Sent these restaurant's menus to Slack
val FAVOURITE_DISHES = listOf("siipibuffet", "siipibuffa") // Use lower case in names

@Scheduled("0, 7, ?, *, MON-FRI, *")
@Get("/menus")
fun scheduledMenus(): String {
    val menus = getMenus(Jsoup.connect(LOUNAAT_INFO_URL).get())
    if (!eduMenuExists(menus, EDU_NAME)) {
        getEduMenu(Jsoup.connect(EDU_URL).get(), getWeekDay())
    }
    val messenger = SlackMessenger()
    createMessages(menus, MENUS_TO_SENT, FAVOURITE_DISHES).forEach { messenger.sendMenus(it) }
    return "Messages sent!"
}
