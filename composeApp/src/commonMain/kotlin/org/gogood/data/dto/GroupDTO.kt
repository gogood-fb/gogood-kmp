package org.gogood.data.dto

import androidx.compose.ui.util.unpackInt1
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDTO(
    val id: String,
    val attributes: String? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val description: String? = null,
    val entities: List<String>? = null,//Note these are just the Ids of the Entries
    val groups: UserListWrapper? = null,
    val name: String? = null,
    val type: String? = null, //This will be an enum
) {
    @Serializable
    data class UserListWrapper(
        val users: List<String>? = null,
    )
}
