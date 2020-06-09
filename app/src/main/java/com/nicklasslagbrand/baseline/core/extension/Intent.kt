package com.nicklasslagbrand.baseline.core.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Activity.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        Toast.makeText(this, "No app found to open $url", Toast.LENGTH_LONG).show()
    }
}
