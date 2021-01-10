package fi.atkpihlainen.lunchWhisperer.utils

import fi.atkpihlainen.lunchWhisperer.model.Dish
import fi.atkpihlainen.lunchWhisperer.model.Menu
import fi.atkpihlainen.lunchWhisperer.model.SpecialDietAbbreviations
import fi.atkpihlainen.lunchWhisperer.model.SpecialDietClasses
import java.util.*

fun eduMenuExists(menus: List<Menu>, edu: String): Boolean {
    val eduMenu = menus.find { menu -> menu.restaurant.name == edu }
    return eduMenu!!.dishes.isNotEmpty()
}

fun parseDish(dishRaw: String, diets: String): Dish {
    return Dish(
        name = sanitizeDishName(dishRaw),
        glutenFree = isGlutenFree(diets),
        milkless = isMilkless(diets),
        lactoseFree = isLactoseFree(diets),
        vegan = isVegan(diets),
        price = setPrice(dishRaw)
    )
}

private fun sanitizeDishName(dish: String): String {
    var dishCleaned: String = dish
    enumValues<SpecialDietAbbreviations>().forEach {
        dishCleaned = dishCleaned.replace(" ${it.value}", " ", ignoreCase = true)
        if (dishCleaned.takeLast(2).equals(" ${it.value}", ignoreCase = true))
            dishCleaned = dishCleaned.dropLast(it.value.length)
    }
    if (dishCleaned.last().isDigit())
        dishCleaned = dishCleaned.dropLast(5).trim() // Drop price
    dishCleaned = dishCleaned.replace(",", "").replace("\\s+".toRegex(), " ")
    return dishCleaned.trim()
}

private fun setPrice(dishRaw: String): Double? {
    val digits = dishRaw.filter { it.isDigit() }
    return if (digits.isBlank()) null else digits.toDouble() / 100
}

private fun isGlutenFree(diets: String): Boolean {
    return diets.contains(SpecialDietClasses.GLUTEN_FREE.value, ignoreCase = true) ||
        diets.contains(SpecialDietAbbreviations.GLUTEN_FREE.value, ignoreCase = false)
}

private fun isMilkless(diets: String): Boolean {
    return diets.contains(SpecialDietClasses.MILKLESS.value, ignoreCase = true) ||
        diets.contains(SpecialDietAbbreviations.MILKLESS.value, ignoreCase = false)
}

private fun isLactoseFree(diets: String): Boolean {
    return diets.contains(SpecialDietClasses.LACTOSE_FREE.value, ignoreCase = true) ||
        diets.contains(SpecialDietAbbreviations.LACTOSE_FREE.value, ignoreCase = false)
}

private fun isVegan(diets: String): Boolean {
    return diets.contains(SpecialDietClasses.VEGAN.value, ignoreCase = true) ||
        diets.contains(SpecialDietAbbreviations.VEGAN.value, ignoreCase = false)
}

fun getWeekDay(): String {
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    return (when (day) {
        2 -> "MAANANTAI"
        3 -> "TIISTAI"
        4 -> "KESKIVIIKKO"
        5 -> "TORSTAI"
        6 -> "PERJANTAI"
        else -> "Viikonloppu"
    })
}
