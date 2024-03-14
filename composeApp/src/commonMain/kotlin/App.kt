import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.ViewModel

@Composable
fun App() {
    PreComposeApp{
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("hi")
            Text("I am seeuol gi")
        }
    }
}
