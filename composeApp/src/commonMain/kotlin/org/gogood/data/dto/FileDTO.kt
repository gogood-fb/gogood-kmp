package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    val id: String,
    val bytes: Int,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val description: String? = null,
    val editable: Boolean? = null,
    val groups: List<GroupDTO>? = null,
    val image: FileImageDTO? = null,
    @SerialName("ip_attribution")
    val ipAttribution: String? = null,
    @SerialName("ip_common")
    val ipCommon: Boolean? = null,
    @SerialName("ip_expiry")
    val ipExpiry: String? = null,
    @SerialName("ip_note")
    val ipNote: String? = null,
    @SerialName("ip_verified")
    val ipVerified: Boolean? = null,
    val locale: String? = null,
    val name: String? = null,
    val point: String? = null,
    val title: String? = null,
    val type: String? = null,
    val url: String? = null,
    val user: UserIdDTO? = null,
) {
    @Serializable
    data class FileImageDTO(
        val width: Int?,
        val height: Int?,
    )
}