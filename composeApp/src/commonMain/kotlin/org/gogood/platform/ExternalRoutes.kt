package org.gogood.platform

import org.gogood.data.model.Location

interface ExternalRoutes {
    fun launchDialer(phoneNumber: String)

    fun launchBrowser(url: String)

    fun launchShare(content: String)

    fun launchMap(location: Location)
}
