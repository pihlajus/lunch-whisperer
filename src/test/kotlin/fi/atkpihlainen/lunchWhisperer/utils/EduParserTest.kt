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
        val menus = getEduMenu(doc)
        assertEquals(3, actual = menus.size)
    }
}

