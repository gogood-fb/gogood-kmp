package org.gogood.data.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

data class Destination(
    val id: String,
    val name: String,
    val country: String,
    val tileImageUrl: String,
    val additionalImageUrls: List<String>,
    val timeZone: TimeZone,
    val location: Location,
    val sustainabilityScore: Int,
) : Tileable {
    override fun toTile() =
        TileData(
            title = "$name, $country",
            subtitle = "",
            description = "",
            imageUrlLarge = additionalImageUrls.first(),
            imageUrlSmall = tileImageUrl,
            additionalImages = additionalImageUrls,
            sustainabilityScore = sustainabilityScore,
            actions =
                listOf(
                    Action.Share(name),
                    Action.Directions(location),
                ),
        )

    fun getDateTimeString(): String {
        val now = Clock.System.now()
        return format.format(now.toLocalDateTime(timeZone))
    }
}

private val format =
    LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        char(' ')
        monthNumber()
        char('/')
        dayOfMonth()
        char('/')
        year()
        char(' ')
        hour()
        char(':')
        minute()
    }
