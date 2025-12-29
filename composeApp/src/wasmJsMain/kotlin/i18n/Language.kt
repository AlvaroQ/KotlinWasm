package i18n

import androidx.compose.runtime.compositionLocalOf

enum class Language(val code: String, val displayName: String) {
    ES("es", "Espanol"),
    EN("en", "English")
}

val LocalLanguage = compositionLocalOf { Language.ES }
