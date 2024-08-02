import androidx.compose.ui.graphics.ImageBitmap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect suspend fun loadNetworkImage1(link: String): ImageBitmap