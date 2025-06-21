package com.example.citrusapp.Main.Home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.citrusapp.ui.theme.blue_green

@Composable
fun Survey() {
    val surveyData = listOf(
        "How satisfied are you with the app so far?" to listOf("Very satisfied", "Satisfied", "Neutral", "Dissatisfied", "Very dissatisfied"),
        "Is the new LMS better than the old one?" to listOf("Yes", "Probably", "I don't know", "No"),
        "What do you think about the chat system that we've implemented?" to listOf("I like it, it's very useful", "It's okay, but not really necessary to add a chat system", "I don't like the idea, its too repetitive", "I don't know"),
        "How easy is the app to use?" to listOf("Very easy", "Easy", "Average", "Difficult", "Very difficult"),
    )

    val totalQuestions = surveyData.size
    var currentQuestionIndex by remember { mutableStateOf(0) }

    var showIntro by remember { mutableStateOf(true) }

    val responses = remember { mutableStateListOf<String?>().apply { repeat(totalQuestions) { add(null) } } }

    var dissatisfactionReason by remember { mutableStateOf("") }
    var lmsReason by remember { mutableStateOf("") }
    var easeOfUseReason by remember { mutableStateOf("") }

    if (showIntro) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Could we have a moment of your time to answer a quick survey? Your feedback would be greatly appreciated.",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { showIntro = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue_green,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Start Survey")
                    }
                }
            }
        }
    }
    else {
        if (currentQuestionIndex < totalQuestions) {
            val (question, options) = surveyData[currentQuestionIndex]

            val showReasonInput = when (currentQuestionIndex) {
                0 -> responses[0] == "Very dissatisfied"
                1 -> responses[1] == "No"
                3 -> responses[3] == "Difficult" || responses[3] == "Very difficult"
                else -> false
            }

            SurveyCardBox(
                question = question,
                options = options,
                currentIndex = currentQuestionIndex,
                total = totalQuestions,
                selectedOption = responses[currentQuestionIndex],
                onOptionSelected = { selected ->
                    responses[currentQuestionIndex] = selected
                    when (currentQuestionIndex) {
                        0 -> if (selected != "Very dissatisfied") dissatisfactionReason = ""
                        1 -> if (selected != "No") lmsReason = ""
                        3 -> if (selected != "Difficult" && selected != "Very difficult") easeOfUseReason = ""
                    }
                },
                onNext = {
                    currentQuestionIndex++
                },
                onBack = {
                    if (currentQuestionIndex > 0) currentQuestionIndex--
                },
                showReasonInput = showReasonInput,
                reasonText = when (currentQuestionIndex) {
                    0 -> dissatisfactionReason
                    1 -> lmsReason
                    3 -> easeOfUseReason
                    else -> ""
                },
                onReasonTextChanged = { text ->
                    when (currentQuestionIndex) {
                        0 -> dissatisfactionReason = text
                        1 -> lmsReason = text
                        3 -> easeOfUseReason = text
                    }
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Thank you for completing the survey!",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun SurveyCardBox(
    question: String,
    options: List<String>,
    currentIndex: Int,
    total: Int,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    onNext: () -> Unit,
    onBack: (() -> Unit)? = null,
    showReasonInput: Boolean = false,
    reasonText: String = "",
    onReasonTextChanged: (String) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.titleMedium
        )

        val targetProgress = (currentIndex + 1).toFloat() / total
        val animatedProgress by animateFloatAsState(
            targetValue = targetProgress,
            animationSpec = tween(durationMillis = 500),
            label = "Progress Animation"
        )

        LinearProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )

        Spacer(modifier = Modifier.height(12.dp))

        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }

        if (showReasonInput) {
            OutlinedTextField(
                value = reasonText,
                onValueChange = onReasonTextChanged,
                label = {
                    Text(
                        if (currentIndex == 0)
                            "Could you let us know what caused your dissatisfaction?"
                        else
                            "Please tell us why so we can further improve our service for you."
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentIndex > 0 && onBack != null) {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }


            Button(
                onClick = onNext,
                enabled = selectedOption != null && (!showReasonInput || reasonText.isNotBlank()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue_green,
                    contentColor = Color.White
                )
            ) {
                Text("Next")
            }

        }
    }
}
