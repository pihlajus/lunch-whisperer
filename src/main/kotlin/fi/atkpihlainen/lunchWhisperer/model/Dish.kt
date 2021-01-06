package fi.atkpihlainen.lunchWhisperer.model

data class Dish(
    var name: String,
    var glutenFree: Boolean? = false,
    var lactoseFree: Boolean? = false,
    var milkless: Boolean? = false,
    var vegan: Boolean? = false,
    var price: Double? = 0.0
)
