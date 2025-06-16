package org.gogood.data

//TODO, let this take in a clientConfig with the platform config variables
class DataStoreConfig() {

    val eleBaseUrl = ELEBASE_URL

    private companion object {
        const val PROJECT_ID = "bfe7dca8-ded7-4599-acba-034c8945acf8"
        const val ELEBASE_URL = "https://cdn.elebase.io/${PROJECT_ID}/v1/"
    }
}
