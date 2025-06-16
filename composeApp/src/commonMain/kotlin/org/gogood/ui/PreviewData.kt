package org.gogood.ui

import kotlinx.datetime.TimeZone
import org.gogood.data.model.ContentItem
import org.gogood.data.model.Destination
import org.gogood.data.model.FavoriteList
import org.gogood.data.model.Location
import org.gogood.data.model.Trip
import kotlin.random.Random

object PreviewData {
    private val montana =
        Destination(
            id = "1",
            name = "Montana",
            country = "USA",
            tileImageUrl = "https://picsum.photos/id/301/200",
            additionalImageUrls =
                listOf(
                    "https://picsum.photos/id/302/600",
                    "https://picsum.photos/id/303/600",
                    "https://picsum.photos/id/304/600",
                    "https://picsum.photos/id/305/600",
                    "https://picsum.photos/id/306/600",
                    "https://picsum.photos/id/307/600",
                    "https://picsum.photos/id/308/600",
                    "https://picsum.photos/id/309/600",
                ),
            timeZone = TimeZone.of("GMT-6"),
            location = Location(46.8797f, -110.3626f),
            sustainabilityScore = 97,
        )

    private val belize =
        Destination(
            id = "2",
            name = "Belize",
            country = "CAM",
            tileImageUrl = "https://picsum.photos/id/311/200",
            additionalImageUrls =
                listOf(
                    "https://picsum.photos/id/312/600",
                    "https://picsum.photos/id/313/600",
                    "https://picsum.photos/id/314/600",
                    "https://picsum.photos/id/315/600",
                ),
            timeZone = TimeZone.of("GMT-6"),
            location = Location(17.1899f, -88.4976f),
            sustainabilityScore = 94,
        )

    val destinations = listOf(montana, belize)

    val contentItems =
        buildList {
            repeat(200) {
                add(createSampleContentItem(it))
            }
        }

    val trips =
        buildList {
            add(createSampleTrip(1, "My Belize Trip", belize.id))
            add(createSampleTrip(2, "My Montana Trip", montana.id))
        }

    val favorites =
        buildList {
            repeat(4) {
                add(createSampleFavoriteList(it))
            }
        }

    private fun createImageUrl(
        id: Int,
        size: Int,
    ) = "https://picsum.photos/id/$id/$size"

    private fun createSampleContentItem(id: Int) =
        ContentItem(
            id = "content-$id",
            destinationId = if (id % 2 == 0) belize.id else montana.id,
            type = ContentItem.Type.entries.random(),
            title = "Sample Title $id",
            subtitle = "Sample Subtitle",
            tileUrlSmall = createImageUrl(id + 100, 300),
            tileUrlMedium = createImageUrl(id + 100, 600),
            additionalImageUrls =
                listOf(
                    createImageUrl(id + 200, 600),
                    createImageUrl(id + 201, 600),
                    createImageUrl(id + 202, 600),
                    createImageUrl(id + 203, 600),
                ),
            location = randomizeLocation(if (id % 2 == 0) belize.location else montana.location),
            contentMarkdown =
                """
                **White Sulphur Springs** is located on *US Hwy 89* at the southern end of the 71-mile Kings Hill Scenic Byway. Soak in three natural hot pools fed by sulphur springs at the Spa Hot Springs Motel. Three pools, 2 outdoor and 1 indoor, are drained, cleaned and refilled every night and they are chemical free. Local stores and restaurants line the streets of White Sulphur Springs. The Castle Museum overlooks the town and is open for tours in the summer. 
                - White Sulphur Springs is known for year round recreation. Camping, bird watching and hiking are great summer activities.
                - Nearby streams and reservoirs lure fishermen. Fall brings out hunters and winter changes the area into a mecca for outdoor recreation including downhill skiing, X-C skiing, snowmobiling and snowshoeing.
                - Summer events include local rodeos July 4 and Labor Day and Red Ants Pants Music Festival the last weekend of July.
                Red Ants Pants brings nationally known musicians to a stage in a pasture on the outskirts of the community and the event typically draws 14,000 people over three days.
                [Sample Link](http://google.com)
                """.trimIndent(),
            sustainabilityScore = Random.nextInt(0, 100),
            phoneNumber = if (id % 5 == 0) "(123) 456-7890" else null,
            website = if (id % 4 == 0) "http://gogoodtravel.org" else null,
        )

    private fun createSampleFavoriteList(id: Int): FavoriteList {
        val size = Random.nextInt(2, 40)
        val items = contentItems.shuffled().take(size)
        val ordinals = (0 until size).toList().shuffled()

        return FavoriteList(
            id = "collection-$id",
            name = "My Favorites $id",
            items = items.zip(ordinals).toMap(),
            sustainabilityScore = Random.nextInt(0, 100),
        )
    }

    private fun createSampleTrip(
        id: Int,
        name: String,
        destinationId: String,
    ): Trip {
        val size = Random.nextInt(2, 40)
        val items = contentItems.filter { it.destinationId == destinationId }.shuffled().take(size)
        val ordinals = (0 until size).toList().shuffled()

        return Trip(
            id = "trip-$id",
            name = name,
            description = "This is a trip description",
            sustainabilityScore = Random.nextInt(0, 100),
            items = items.zip(ordinals).toMap(),
        )
    }

    private fun randomizeLocation(location: Location) =
        Location(
            latitude = location.latitude + (Random.nextFloat() * 2) - 2,
            longitude = location.longitude + (Random.nextFloat() * 2) - 2,
        )
}
