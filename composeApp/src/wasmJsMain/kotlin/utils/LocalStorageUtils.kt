package utils

import kotlinx.browser.window

object LocalStorage {
    fun setItem(key: String, value: String) {
        window.localStorage.setItem(key, value)
    }

    fun getItem(key: String): String? {
        return window.localStorage.getItem(key)
    }

    fun removeItem(key: String) {
        window.localStorage.removeItem(key)
    }
}

const val THEME_KEY = "portfolio_theme"
const val THEME_DARK = "dark"
const val THEME_LIGHT = "light"

const val LANGUAGE_KEY = "portfolio_lang"
const val LANG_ES = "es"
const val LANG_EN = "en"
