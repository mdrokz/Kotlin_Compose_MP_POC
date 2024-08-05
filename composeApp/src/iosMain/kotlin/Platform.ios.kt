import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun ByteArray.toImageBitmap(): ImageBitmap =
    Image.makeFromEncoded(this).toComposeImageBitmap()

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual suspend fun loadNetworkImage1(link: String): ImageBitmap {
    val client = HttpClient()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.get(link)
            val bytes = response.body<ByteArray>()

            val bitmap = bytes.toImageBitmap()
            bitmap
        } catch (e: Exception) {
            throw e
        } finally {
            client.close()
        }
    }
}