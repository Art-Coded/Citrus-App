import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoScrollPager(
    pagerState: PagerState,
    delayMillis: Long = 4000L
) {
    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(delayMillis)

            // Prevent multiple scroll calls while the pager is settling
            if (!pagerState.isScrollInProgress) {
                val currentPage = pagerState.currentPage
                val targetPage = currentPage + 1

                // Use scroll instead of animate if animation causes lag
                try {
                    pagerState.animateScrollToPage(targetPage)
                } catch (e: Exception) {
                    // Fail-safe to prevent crash if scroll is interrupted
                }
            }
        }
    }
}
