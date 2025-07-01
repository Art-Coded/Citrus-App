import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.citrusapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun BottomNavItem(
    label: String,
    animationFile: String?,
    isSelected: Boolean,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    val fileName = animationFile?.let {
        "${it}_${if (isDarkTheme) "light" else "dark"}"
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(if (fileName != null) getRawResId(fileName) else 0)
    )
    val lottieAnimState = rememberLottieAnimatable()
    val coroutineScope = rememberCoroutineScope()

    val iconAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.5f,
        animationSpec = tween(300)
    )

    val user = FirebaseAuth.getInstance().currentUser
    val profileImageUrl = user?.photoUrl


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
                if (animationFile != null) {
                    coroutineScope.launch {
                        lottieAnimState.animate(composition)
                    }
                }
            }
            .padding(vertical = 4.dp)
    ) {
        if (animationFile != null) {
            LottieAnimation(
                composition = composition,
                progress = { lottieAnimState.progress },
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer(alpha = iconAlpha)
            )
        } else {
            if (profileImageUrl != null) {
                AsyncImage(
                    model = profileImageUrl,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .graphicsLayer(alpha = iconAlpha)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Account Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer(alpha = iconAlpha),
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }
        }

        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = iconAlpha)
        )
    }
}

fun getRawResId(fileName: String): Int {
    return when (fileName) {
        "home_dark" -> R.raw.home_dark
        "home_light" -> R.raw.home_light
        "inbox_dark" -> R.raw.inbox_dark
        "inbox_light" -> R.raw.inbox_light
        "article_icon_dark" -> R.raw.article_icon_dark
        "article_icon_light" -> R.raw.article_icon_light
        "newspaper_dark" -> R.raw.newspaper_dark
        "newspaper_light" -> R.raw.newspaper_light
        else -> R.raw.home_dark // default fallback
    }
}
