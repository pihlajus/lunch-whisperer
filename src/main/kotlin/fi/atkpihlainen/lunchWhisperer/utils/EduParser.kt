package fi.atkpihlainen.lunchWhisperer.utils

import fi.atkpihlainen.lunchWhisperer.model.Dish
import org.jsoup.nodes.Document

fun getEduMenu(doc: Document, weekDay: String): List<Dish> {
    val lunchesRaw = doc
        .select("#pirkkala")
        .select(".modal-body")[0]
        .select("p")
        .filter { el -> el.text().contains(weekDay, ignoreCase = true) }[0]
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

