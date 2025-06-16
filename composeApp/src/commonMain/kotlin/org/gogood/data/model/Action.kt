package org.gogood.data.model

import gogood.composeapp.generated.resources.Res
import gogood.composeapp.generated.resources.action_call
import gogood.composeapp.generated.resources.action_edit
import gogood.composeapp.generated.resources.action_fav
import gogood.composeapp.generated.resources.action_map
import gogood.composeapp.generated.resources.action_share
import gogood.composeapp.generated.resources.action_trip
import gogood.composeapp.generated.resources.action_web
import gogood.composeapp.generated.resources.ico_add_fav
import gogood.composeapp.generated.resources.ico_add_trip
import gogood.composeapp.generated.resources.ico_call
import gogood.composeapp.generated.resources.ico_directions
import gogood.composeapp.generated.resources.ico_edit
import gogood.composeapp.generated.resources.ico_share
import gogood.composeapp.generated.resources.ico_web
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class Action(
    val iconRes: DrawableResource,
    val labelRes: StringResource,
) {
    data class Call(val phoneNumber: String) : Action(
        Res.drawable.ico_call,
        Res.string.action_call,
    )

    data class Website(val url: String) : Action(
        Res.drawable.ico_web,
        Res.string.action_web,
    )

    data class Share(val content: String) : Action(
        Res.drawable.ico_share,
        Res.string.action_share,
    )

    data object AddToTrip : Action(
        Res.drawable.ico_add_trip,
        Res.string.action_trip,
    )

    data object AddToFavorites : Action(
        Res.drawable.ico_add_fav,
        Res.string.action_fav,
    )

    data class Directions(val location: Location) : Action(
        Res.drawable.ico_directions,
        Res.string.action_map,
    )

    data object Edit : Action(
        Res.drawable.ico_edit,
        Res.string.action_edit,
    )
}
