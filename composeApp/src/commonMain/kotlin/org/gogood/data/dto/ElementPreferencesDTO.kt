package org.gogood.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ElementPreferencesDTO(
    @SerialName("file_type_group")
    val fileTypeGroup: String? = null,
    val quantity: List<Int>? = null,
    val default: Boolean? = null,
    val duration: List<Int>? = null,
    @SerialName("data_type")
    val dataType: String? = null,
    val interval: Boolean? = null,
    val types: List<String>? = null,
    val dimensions: Int? = null,
    val point: String? = null,
    val zoom: Int? = null,
    val length: List<Int>? = null,
    val unique: Boolean? = null,
    val integer: Boolean? = null,
    val mode: String? = null,
    @SerialName("editor_tools")
    val editorTools: EditorTools? = null,
    @SerialName("iana_type")
    val ianaType:String? = null,
    @SerialName("text_editor")
    val textEditor:Int? = null
) {
    @Serializable
    data class EditorTools(
        @SerialName("blockquote")
        val blockQuote: Boolean? = null,
        val bold: Boolean? = null,
        val code: Boolean? = null,
        @SerialName("dual-pane")
        val dualPane: Boolean? = null,
        @SerialName("edit-redo")
        val editRedo: Boolean? = null,
        @SerialName("edit-undo")
        val editUndo: Boolean? = null,
        val expand: Boolean? = null,
        val header: Boolean? = null,
        @SerialName("horizontal-line")
        val horizontalLine: Boolean? = null,
        val image: Boolean? = null,
        val italic: Boolean? = null,
        @SerialName("line-through")
        val lineThrough: Boolean? = null,
        val link: Boolean? = null,
        @SerialName("list-ordered")
        val listOrdered: Boolean? = null,
        @SerialName("list-unordered")
        val listUnordered: Boolean? = null,
        val preview: Boolean? = null,
        val table: Boolean? = null,
    )
}
