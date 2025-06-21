import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OrbitLoadingIndicator(
    modifier: Modifier = Modifier,
    dotColor: Color = Color.White,
    dotSize: Float = 8f,
    orbitRadius: Float = 24f,
    orbitDurationMillis: Int = 1000,
    dotCount: Int = 3
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Orbit")
    val angleOffset = 360f / dotCount

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        for (i in 0 until dotCount) {
            val angle by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = orbitDurationMillis, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = "OrbitAngle$i"
            )

            val radians = Math.toRadians((angle + i * angleOffset).toDouble())
            val xOffset = (orbitRadius * kotlin.math.cos(radians)).toFloat()
            val yOffset = (orbitRadius * kotlin.math.sin(radians)).toFloat()

            Box(
                modifier = Modifier
                    .offset(x = xOffset.dp, y = yOffset.dp)
                    .size(dotSize.dp)
                    .background(dotColor, shape = RoundedCornerShape(50))
            )
        }
    }
}

