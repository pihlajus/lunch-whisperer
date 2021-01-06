package fi.atkpihlainen.lunchWhisperer.utils

import org.jsoup.Jsoup
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LounaatInfoParserTest {
    @Test
    fun testGetMenus() {
        val file = this::class.java.classLoader.getResource("lounaat_info.html").readText()
        val doc = Jsoup.parse(file, "test")
        val menus = getMenus(doc)
        assertEquals(16, menus.size)
        assertEquals("Ninan Keittiö Veska", menus[0].restaurant.name)
        assertEquals("Parmesan-valkosipulibroileria ja riisiä", menus[0].dishes[0].name)
        assertEquals("Edun Herkkukeidas Pirkkala", menus[2].restaurant.name)
        assertEquals("Kreikkalaiset pyörykät", menus[2].dishes[1].name)
        assertEquals(true, menus[0].dishes[0].milkless)
        assertEquals(false, menus[0].dishes[0].vegan)
        assertEquals(10.0, menus[0].dishes[0].price)
    }
}

