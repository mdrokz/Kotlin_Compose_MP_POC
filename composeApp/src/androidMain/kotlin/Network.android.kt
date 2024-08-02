import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun loadNetworkImage(link: String): ImageBitmap {
    val client = HttpClient()

    return withContext(Dispatchers.IO) {
        try {
            val response = client.get(link)
            val bytes = response.body<ByteArray>()
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            bitmap.asImageBitmap()
        } catch (e: Exception) {
            throw e
        } finally {
            client.close()
        }
    }
}
