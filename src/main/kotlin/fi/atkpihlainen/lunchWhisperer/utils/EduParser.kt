package fi.atkpihlainen.lunchWhisperer.utils

import fi.atkpihlainen.lunchWhisperer.model.Dish
import org.jsoup.nodes.Document
import java.util.*


fun getEduMenu(doc: Document): List<Dish> {
    val lunchesRaw = doc
        .select("#pirkkala")
        .select(".modal-body")[0]
        .select("p")
        .filter { el -> el.text().contains(getWeekDay(), ignoreCase = true) }[0]
        .toString()
    return lunchesRaw
        .split("<br>")
        .filter { el -> !el.contains("strong", ignoreCase = true) }
        .map { dishRaw ->
            parseDish(parseEdusDiets(dishRaw).first, parseEdusDiets(dishRaw).second)
        }
}

private fun parseEdusDiets(dishRaw: String): Pair<String, String> {
    val dietMatcher = "\\(([^)]+)\\)".toRegex()
    val diets = dietMatcher.find(dishRaw)?.groupValues
    return if (diets.isNullOrEmpty()) Pair(dishRaw, "")
    else
        Pair(
            dishRaw.replace(diets[0], "").replace("</p>", ""),
            diets[1]
        )
}

private fun getWeekDay(): String {
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
