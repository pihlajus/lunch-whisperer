package fi.atkpihlainen.lunchWhisperer.model

data class Restaurant(
    var name: String,
    var uri: String? = null,
    var address: String? = null
)
