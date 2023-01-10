import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlin.random.Random

@ExperimentalComposeUiApi
@Composable
@Preview
fun App() {

    var isUserStupid by remember { mutableStateOf(false) }
    var isUserLegend by remember { mutableStateOf(false) }
    var noButtonCoordinates by remember { mutableStateOf(IntOffset(470, 430)) }
    var noButtonSize by remember { mutableStateOf(IntSize(0, 0)) }

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Are you stupid? \uD83E\uDD14",
                modifier = Modifier.offset(400.dp, 400.dp)
            )

            Button(
                onClick = {
                    isUserStupid = true
                    isUserLegend = false
                },
                modifier = Modifier.offset(400.dp, 430.dp)
            ) {
                Text("YES")
            }

            Button(
                onClick = {
                    isUserLegend = true
                    isUserStupid = false
                },
                modifier = Modifier
                    .offset { noButtonCoordinates }
                    .onPointerEvent(PointerEventType.Enter) {
                        val x = Random.nextInt(0, 1000 - noButtonSize.width - 50)
                        val y = Random.nextInt(0, 1000 - noButtonSize.height - 50)
                        isUserLegend = false
                        isUserStupid = false
                        noButtonCoordinates = IntOffset(x, y)
                    }
                    .onGloballyPositioned {
                        noButtonSize = it.size
                    }

            ) {
                Text("NO")
            }

            when {
                isUserStupid -> {
                    Text(
                        text = "I knew it \uD83D\uDE02",
                        modifier = Modifier.offset(400.dp, 490.dp)
                    )
                }

                isUserLegend -> {
                    Text(
                        text = "You are a legend \uD83D\uDE0D",
                        modifier = Modifier.offset(400.dp, 490.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
fun main() = application {
    val state = WindowState(size = DpSize(1000.dp, 1080.dp))
    Window(onCloseRequest = ::exitApplication, state = state, resizable = false) {
        App()
    }
}
