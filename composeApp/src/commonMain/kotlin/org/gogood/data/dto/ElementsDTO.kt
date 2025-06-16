package org.gogood.data.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

@Serializable(with = ElementsDTOSerializer::class)
data class ElementsDTO(
    val id: String,
    val attributes: String? = null, //TODO,
    val data: ElementDataDTO? = null,
    @SerialName("date_time")
    val dateTime: DateTimeDTO? = null,
    val description: String? = null,
    val enabled: Boolean? = null,
    val geo: GeoDTO? = null,
    val groups: GroupsDTO? = null,
    val contacts: List<ContactDTO>? = null,
    val name: String? = null,
    val optional: Boolean? = null,
    val options: List<ElementOptionDTO>? = null,
    val preferences: ElementPreferencesDTO? = null,
    val type: String? = null, //this will be an enum,
    val types: List<TypeDTO<TypeDTO.TypeGroupIdsDTO>>? = null,//List of Ids for the Entry Types associated with this element
)


object ElementsDTOSerializer : KSerializer<ElementsDTO> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("ElementsDTO") {
            element("id", serialDescriptor<String>())
            element("attributes", serialDescriptor<String?>(), isOptional = true)
            element("data", buildClassSerialDescriptor("Any"), isOptional = true)
            element("date_time", serialDescriptor<DateTimeDTO?>(), isOptional = true)
            element("description", serialDescriptor<String?>(), isOptional = true)
            element("enabled", serialDescriptor<Boolean?>(), isOptional = true)
            element("geo", serialDescriptor<GeoDTO?>(), isOptional = true)
            element("groups", serialDescriptor<GroupsDTO?>(), isOptional = true)
            element("name", serialDescriptor<String?>(), isOptional = true)
            element("optional", serialDescriptor<Boolean?>(), isOptional = true)
            element("options", serialDescriptor<List<ElementOptionDTO>?>(), isOptional = true)
            element("preferences", serialDescriptor<ElementPreferencesDTO?>(), isOptional = true)
            element("type", serialDescriptor<String?>(), isOptional = true)
            element(
                "types",
                serialDescriptor<TypeDTO<TypeDTO.TypeGroupIdsDTO>?>(),
                isOptional = true,
            )
            element("contacts", serialDescriptor<List<ContactDTO>?>(), isOptional = true)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): ElementsDTO = decoder.decodeStructure(descriptor) {

        var isDone = false
        var id: String? = null
        var attributes: String? = null
        var data: ElementDataDTO? = null
        var dateTime: DateTimeDTO? = null
        var description: String? = null
        var enabled: Boolean? = null
        var geo: GeoDTO? = null
        var groups: GroupsDTO? = null
        var name: String? = null
        var optional: Boolean? = null
        var options: List<ElementOptionDTO>? = null
        var preferences: ElementPreferencesDTO? = null
        var type: String? = null
        var types: List<TypeDTO<TypeDTO.TypeGroupIdsDTO>>? = null
        var contacts: List<ContactDTO>? = null
        while (!isDone) {
            when (val index = decodeElementIndex(descriptor)) {
                CompositeDecoder.DECODE_DONE -> {
                    isDone = true
                }

                0 -> {
                    id = decodeStringElement(descriptor, index)
                }

                1 -> {
                    attributes =
                        decodeNullableSerializableElement(descriptor, index, String.serializer())
                }

                2 -> {
                    type?.let {
                        when (type) {
                            "boolean" -> {
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    Boolean.serializer(),
                                )
                                data = ElementDataDTO(boolean = temp)
                            }

                            "contact" -> {
                                val listSerializer = ListSerializer(ContactDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(contact = temp)
                            }

                            "datetime" -> {
                                val listSerializer = ListSerializer(ListSerializer(String.serializer()))
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    listSerializer,
                                )
                                data = ElementDataDTO(dateTime = temp)
                            }

                            "embed" -> {
                                val listSerializer = ListSerializer(EmbedDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(embed = temp)
                            }

                            "entry" -> {
                                val listSerializer = ListSerializer(EntryDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(entry = temp)
                            }

                            "file" -> {
                                val listSerializer = ListSerializer(FileDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(file = temp)
                            }

                            "geo" -> {
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    GeoDTO.serializer(),
                                )
                                data = ElementDataDTO(geo = temp)
                            }

                            "id" -> {
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    String.serializer(),
                                )
                                data = ElementDataDTO(id = temp)
                            }

                            "location" -> {
                                val listSerializer = ListSerializer(LocationDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(locations = temp)
                            }

                            "number" -> {
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    Double.serializer(),
                                )
                                data = ElementDataDTO(number = temp)
                            }

                            "option" -> {
                                val listSerializer = ListSerializer(ElementOptionDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(option = temp)
                            }

                            "phone" -> {
                                val listSerializer = ListSerializer(PhoneDTO.serializer())
                                val temp =
                                    decodeNullableSerializableElement(
                                        descriptor,
                                        index,
                                        listSerializer,
                                    )
                                data = ElementDataDTO(phone = temp)
                            }

                            "text" -> {
                                val temp = decodeNullableSerializableElement(
                                    descriptor,
                                    index,
                                    String.serializer(),
                                )
                                data = ElementDataDTO(text = temp)
                            }
                        }
                    }
                }

                3 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        DateTimeDTO.serializer(),
                    )
                    dateTime = temp
                }

                4 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        String.serializer(),
                    )
                    description = temp
                }

                5 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        Boolean.serializer(),
                    )
                    enabled = temp
                }

                6 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        GeoDTO.serializer(),
                    )
                    geo = temp
                }

                7 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        GroupsDTO.serializer(),
                    )
                    groups = temp
                }

                8 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        String.serializer(),
                    )
                    name = temp
                }

                9 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        Boolean.serializer(),
                    )
                    optional = temp
                }

                10 -> {
                    val listSerializer = ListSerializer(ElementOptionDTO.serializer())
                    val temp =
                        decodeNullableSerializableElement(descriptor, index, listSerializer)
                    options = temp
                }

                11 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        ElementPreferencesDTO.serializer(),
                    )
                    preferences = temp
                }

                12 -> {
                    val temp = decodeNullableSerializableElement(
                        descriptor,
                        index,
                        String.serializer(),
                    )
                    type = temp
                }

                13 -> {
                    val listSerializer =
                        ListSerializer(TypeDTO.serializer(TypeDTO.TypeGroupIdsDTO.serializer()))
                    val temp =
                        decodeNullableSerializableElement(descriptor, index, listSerializer)
                    types = temp
                }

                14 -> {
                    val listSerializer =
                        ListSerializer(ContactDTO.serializer())
                    val temp =
                        decodeNullableSerializableElement(descriptor, index, listSerializer)
                    contacts = temp
                }

                else -> {
                    isDone = true
                }
            }
        }

        ElementsDTO(
            id ?: "",
            attributes, data, dateTime, description, enabled, geo,
            groups, contacts, name, optional, options, preferences, type,
            types,
        )
    }

    override fun serialize(encoder: Encoder, value: ElementsDTO) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.id)
            value.attributes?.let {
                encodeStringElement(descriptor, 1, it)
            }
            value.data?.let {
                val dataIndex = 2
                if (it.boolean != null) {
                    encodeBooleanElement(descriptor, dataIndex, it.boolean)
                } else if (it.contact != null) {
                    val listSerializer = ListSerializer(ContactDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.contact)
                } else if (it.dateTime != null) {
                    val listSerializer = ListSerializer(ListSerializer(String.serializer()))
                    encodeSerializableElement(
                        descriptor,
                        dataIndex,
                        listSerializer,
                        it.dateTime,
                    )
                } else if (it.embed != null) {
                    val listSerializer = ListSerializer(EmbedDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.embed)
                } else if (it.entry != null) {
                    val listSerializer = ListSerializer(EntryDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.entry)
                } else if (it.file != null) {
                    val listSerializer = ListSerializer(FileDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.file)
                } else if (it.geo != null) {
                    encodeSerializableElement(
                        descriptor,
                        dataIndex,
                        GeoDTO.serializer(),
                        it.geo,
                    )
                } else if (it.id != null) {
                    encodeStringElement(descriptor, dataIndex, it.id)
                } else if (it.locations != null) {
                    val listSerializer = ListSerializer(LocationDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.locations)
                } else if (it.number != null) {
                    encodeDoubleElement(descriptor, dataIndex, it.number)
                } else if (it.option != null) {
                    val listSerializer = ListSerializer(ElementOptionDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.option)
                } else if (it.phone != null) {
                    val listSerializer = ListSerializer(PhoneDTO.serializer())
                    encodeSerializableElement(descriptor, dataIndex, listSerializer, it.phone)
                } else if (it.text != null) {
                    encodeStringElement(descriptor, dataIndex, it.text)
                }
            }

            value.dateTime?.let {
                encodeSerializableElement(descriptor, 3, DateTimeDTO.serializer(), it)
            }

            value.description?.let {
                encodeStringElement(descriptor, 4, it)
            }

            value.enabled?.let {
                encodeBooleanElement(descriptor, 5, it)
            }

            value.geo?.let {
                encodeSerializableElement(descriptor, 6, GeoDTO.serializer(), it)
            }

            value.groups?.let {
                encodeSerializableElement(descriptor, 7, GroupsDTO.serializer(), it)
            }

            value.name?.let {
                encodeStringElement(descriptor, 8, it)
            }

            value.optional?.let {
                encodeBooleanElement(descriptor, 9, it)
            }
            value.options?.let {
                val listSerializer =
                    ListSerializer(ElementOptionDTO.serializer())
                encodeSerializableElement(descriptor, 10, listSerializer, it)
            }
            value.preferences?.let {
                encodeSerializableElement(
                    descriptor,
                    11,
                    ElementPreferencesDTO.serializer(),
                    it,
                )
            }
            value.type?.let {
                encodeStringElement(descriptor, 12, it)
            }
            value.types?.let {
                val listSerializer =
                    ListSerializer(TypeDTO.serializer(TypeDTO.TypeGroupIdsDTO.serializer()))
                encodeSerializableElement(descriptor, 13, listSerializer, it)
            }
            value.contacts?.let {
                val listSerializer =
                    ListSerializer(ContactDTO.serializer())
                encodeSerializableElement(descriptor, 14, listSerializer, it)
            }
        }
    }
}