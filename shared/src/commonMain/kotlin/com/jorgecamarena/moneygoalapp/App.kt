import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jorgecamarena.moneygoalapp.presentation.ui.feature.home.HomeScreen

@Composable
fun App() = MaterialTheme {
    Navigator(HomeScreen())
}

expect fun getPlatformName(): String