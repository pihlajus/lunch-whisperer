package fi.atkpihlainen.lunchWhisperer.utils

import fi.atkpihlainen.lunchWhisperer.model.Dish
import fi.atkpihlainen.lunchWhisperer.model.Menu
import fi.atkpihlainen.lunchWhisperer.model.Restaurant
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

fun getMenus(doc: Document): List<Menu> {
    return doc.select("div[class^=menu item ]")
        .map { extractMenuData(it) }
}

private fun extractMenuData(menuElement: Element): Menu {
    val restaurant = Restaurant(name = menuElement.getElementsByTag("h3")[0].text())
    val dishes: List<Dish> = menuElement.getElementsByTag("li")
        .filter { it.getElementsByClass("dish").isNotEmpty() }
        .map {
            parseDish(
                it.getElementsByClass("dish").text(),
                it.attributes().toString()
            )
        }
    return Menu(restaurant = restaurant, dishes = dishes)
}

