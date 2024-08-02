import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.json
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
data class Rating(val rate: Double, val count: Int)

@Serializable
data class Product (
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String,
    val rating: Rating
)

const val fakeStoreUrl = "https://fakestoreapi.com"




@Composable
@Preview
fun Products() {

    var searchTerm by remember { mutableStateOf("") }

    var productList by remember { mutableStateOf<List<Product>>(emptyList()) }

    val products = remember(searchTerm,productList) {
        productList.filter { product ->
            product.title.trim().contains(searchTerm.trim(), ignoreCase = true)
        }
    }

    val ktor = HttpClient() {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = false
                    prettyPrint = true
                }
            )
        }
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit, block = {
        scope.launch {
            try {
                println("Fetching data...")
                val response = ktor.get("$fakeStoreUrl/products")
                productList = response.body<List<Product>>()
                println(productList)
            } catch (e: Exception) {
                println(e)
            }
        }
    })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = searchTerm,
            onValueChange = {
               searchTerm = it

                            },
            label = { Text("Search") }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 200.dp)
        ) {
            items(products, key = {product -> product.id}) {
                product -> CardLayout(product)
            }
        }
    }
}