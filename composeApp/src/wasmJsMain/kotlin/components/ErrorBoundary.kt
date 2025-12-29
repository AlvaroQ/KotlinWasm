package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.CyberpunkThemeColors

/**
 * Provides error state management for components.
 * Components can use this to track and display errors gracefully.
 */
class ErrorState {
    var error: Throwable? by mutableStateOf(null)
        private set

    var hasError: Boolean by mutableStateOf(false)
        private set

    fun setError(throwable: Throwable) {
        error = throwable
        hasError = true
        logError("Component error: ${throwable.message}")
    }

    fun clearError() {
        error = null
        hasError = false
    }
}

@Composable
fun rememberErrorState(): ErrorState {
    return remember { ErrorState() }
}

/**
 * Error fallback UI component.
 * Use this when a section fails to load or encounters an error.
 */
@Composable
fun ErrorFallback(
    error: Throwable? = null,
    message: String = "Something went wrong loading this section.",
    onRetry: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CyberpunkThemeColors.card)
            .border(
                1.dp,
                CyberpunkThemeColors.neonMagenta.copy(alpha = 0.5f),
                RoundedCornerShape(12.dp)
            )
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "[ ERROR ]",
                style = MaterialTheme.typography.h5,
                color = CyberpunkThemeColors.neonMagenta,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = message,
                style = MaterialTheme.typography.body1,
                color = CyberpunkThemeColors.textSecondary,
                textAlign = TextAlign.Center
            )

            if (error != null) {
                Text(
                    text = "> ${error.message ?: "Unknown error"}",
                    style = MaterialTheme.typography.caption,
                    color = CyberpunkThemeColors.textSecondary.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }

            if (onRetry != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyberpunkThemeColors.neonCyan.copy(alpha = 0.2f))
                        .border(1.dp, CyberpunkThemeColors.neonCyan, RoundedCornerShape(8.dp))
                        .clickable { onRetry() }
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "[ RETRY ]",
                        style = MaterialTheme.typography.button,
                        color = CyberpunkThemeColors.neonCyan,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Log errors to browser console
private fun logError(message: String) {
    js("console.error(message)")
}
