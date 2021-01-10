package fi.atkpihlainen.lunchWhisperer.messages

import fi.atkpihlainen.lunchWhisperer.model.Menu

fun createMessages(menus: List<Menu>, restaurants: List<String>, favouriteDishes: List<String>): MutableSet<String> {
    val messages: MutableSet<String> = getFavouriteRestaurantsMessages(menus, restaurants)
    messages.addAll(getFavouriteDishMessages(menus, favouriteDishes))
    return messages
}

private fun getFavouriteDishMessages(menus: List<Menu>, favouriteDishes: List<String>): MutableSet<String> {
    val messages: MutableSet<String> = mutableSetOf()
    menus.forEach() { menu ->
        menu.dishes.forEach dishes@ { dish ->
            if (favouriteDishes.contains(dish.name.toLowerCase())) {
                messages.add(createMessage(menu))
                return@dishes
            }
        }
    }
    return messages
}

private fun createMessage(menu: Menu): String {
    var msg = "*${menu.restaurant.name}*\n"
    menu.dishes.forEach { dish ->
        msg += "${dish.name}\n"
    }
    return msg
}

private fun getFavouriteRestaurantsMessages(menus: List<Menu>, restaurants: List<String>): MutableSet<String> {
    val messages: MutableSet<String> = mutableSetOf()
    menus
        .filter { menu -> restaurants.contains(menu.restaurant.name) }
        .forEach { menu ->
            messages.add(createMessage(menu))
        }
    return messages
}
