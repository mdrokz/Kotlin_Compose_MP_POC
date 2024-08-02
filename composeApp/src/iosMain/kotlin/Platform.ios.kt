import androidx.compose.ui.graphics.ImageBitmap
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual suspend fun loadNetworkImage1(link: String): ImageBitmap {
    TODO("Not yet implemented")
}