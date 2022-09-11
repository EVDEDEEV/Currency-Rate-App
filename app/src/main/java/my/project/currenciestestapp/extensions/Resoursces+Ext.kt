package my.project.currenciestestapp.extensions

import android.content.res.Resources
import my.project.currenciestestapp.BuildConfig
import my.project.currenciestestapp.R
import java.util.*

fun Resources.getFlagResource(flagName: String): Int {
    val id = getIdentifier(
        "_${flagName.lowercase(Locale.ROOT)}",
        "drawable",
        BuildConfig.APPLICATION_ID
    )
    if (id == 0) {
        return R.drawable._no_flag
    }
    return id
}