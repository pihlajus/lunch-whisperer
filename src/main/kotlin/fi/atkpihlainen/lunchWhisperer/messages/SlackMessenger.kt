package fi.atkpihlainen.lunchWhisperer.messages

import com.slack.api.Slack
import fi.atkpihlainen.lunchWhisperer.SLACK_CHANNEL_ID
import fi.atkpihlainen.lunchWhisperer.SLACK_OAUTH_TOKEN

class SlackMessenger : Messenger {
    override fun sendMenus(message: String) {
        val slack = Slack.getInstance()
        slack.methods(SLACK_OAUTH_TOKEN).chatPostMessage { req ->
            req.channel(SLACK_CHANNEL_ID).text(message)
        }
    }
}
