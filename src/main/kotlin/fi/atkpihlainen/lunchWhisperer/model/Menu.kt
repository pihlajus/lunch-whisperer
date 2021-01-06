package fi.atkpihlainen.lunchWhisperer.model

data class Menu(
    var restaurant: Restaurant,
    var dishes: List<Dish>
)
