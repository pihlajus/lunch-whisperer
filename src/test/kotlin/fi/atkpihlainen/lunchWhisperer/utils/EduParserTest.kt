package fi.atkpihlainen.lunchWhisperer.utils

import org.jsoup.Jsoup
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EduParserTest {
    @Test
    fun testGetMenus() {
        val file = this::class.java.classLoader.getResource("edu.html").readText()
        val doc = Jsoup.parse(file, "test")
        val dishes = getEduMenu(doc, "keskiviikko")
        assertEquals(3, actual = dishes.size)
        assertEquals("Auralohi", dishes[0].name)
        assertEquals("Jauheliha pippuripihvi", dishes[1].name)
        assertEquals(true, dishes[0].lactoseFree)
        assertEquals(false, dishes[0].milkless)
    }
}

