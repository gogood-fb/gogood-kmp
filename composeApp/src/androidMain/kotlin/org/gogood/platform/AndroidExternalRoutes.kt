package org.gogood.platform

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import org.gogood.data.model.Location

class AndroidExternalRoutes(private val context: Context) : ExternalRoutes {
    override fun launchDialer(phoneNumber: String) {
        val uri = Uri.parse("tel:$phoneNumber")
        val intent =
            Intent(Intent.ACTION_DIAL).apply {
                data = uri
            }
        if (intent.resolveActivity(context.packageManager) != null) {
            startActivity(context, intent, null)
        }
    }

    override fun launchBrowser(url: String) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(context.packageManager) != null) {
            startActivity(context, intent, null)
        }
    }

    override fun launchShare(content: String) {
        val sendIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
                type = "text/plain"
            }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(context, shareIntent, null)
    }

    override fun launchMap(location: Location) {
        val uri = Uri.parse("geo:${location.latitude},${location.longitude}")
        val intent =
            Intent(Intent.ACTION_VIEW).apply {
                data = uri
            }
        if (intent.resolveActivity(context.packageManager) != null) {
            startActivity(context, intent, null)
        }
    }
}
