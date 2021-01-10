package fi.atkpihlainen.lunchWhisperer.utils

import fi.atkpihlainen.lunchWhisperer.messages.createMessages
import fi.atkpihlainen.lunchWhisperer.model.Dish
import fi.atkpihlainen.lunchWhisperer.model.Menu
import fi.atkpihlainen.lunchWhisperer.model.Restaurant
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MessageCreatorTest {
    private val curryBroiler = Dish(
        name = "Currybroileria ja riisiä",
        glutenFree = true,
        lactoseFree = true,
        milkless = true,
        vegan = false,
        price = 9.8
    )
    private val kalakukko = Dish(
        name = "Kalakukko",
        glutenFree = true,
        lactoseFree = true,
        milkless = true,
        vegan = false,
        price = 9.8
    )
    private val jauhelihaKastike = Dish(
        name = "Jauhelihakastike",
        glutenFree = true,
        lactoseFree = true,
        milkless = true,
        vegan = false,
        price = 9.8
    )
    private val menus = listOf(
        Menu(
            restaurant = Restaurant(name = "Ninan Keittiö Veska", uri = null, address = null),
            dishes = listOf(curryBroiler, kalakukko, jauhelihaKastike)
        ),
        Menu(
            restaurant = Restaurant(name = "Edun Herkkukeidas Pirkkala", uri = null, address = null),
            dishes = listOf(curryBroiler, kalakukko)
        ),
        Menu(
            restaurant = Restaurant(name = "Siipiweikot", uri = null, address = null),
            dishes = listOf(kalakukko, jauhelihaKastike)
        ),
        Menu(
            restaurant = Restaurant(name = "Null", uri = null, address = null),
            dishes = listOf(kalakukko)
        )
    )


    @Test
    fun testCreateMessage() {
        val validRestaurants = listOf("Ninan Keittiö Veska", "Edun Herkkukeidas Pirkkala")
        val favouriteDishes = listOf("Siipibuffet", "jauhelihakastike")
        val messages = createMessages(menus, validRestaurants, favouriteDishes)
        assertEquals(
            "*Ninan Keittiö Veska*\nCurrybroileria ja riisiä\nKalakukko\nJauhelihakastike\n",
            messages.elementAt(0)
        )
        assertEquals("*Edun Herkkukeidas Pirkkala*\nCurrybroileria ja riisiä\nKalakukko\n", messages.elementAt(1))
        assertEquals(3, messages.size)
    }
}

