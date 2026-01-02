import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.browser.window
import i18n.Strings
import kotlinx.coroutines.launch
import org.w3c.dom.events.Event
import theme.CyberpunkTheme
import theme.CyberpunkThemeColors
import theme.ThemeMode
import i18n.Language
import i18n.LocalLanguage
import utils.LocalStorage
import utils.THEME_KEY
import utils.THEME_DARK
import utils.THEME_LIGHT
import utils.LANGUAGE_KEY
import utils.LANG_ES
import utils.LANG_EN
import components.NavigationMenu
import components.Section
import components.LocalScrollPosition
import components.LocalViewportHeight
import components.AnimatedSection
import components.AnimationType
import components.LazySection

// Screen size breakpoints
val LocalScreenWidth = compositionLocalOf { 1920 }

// Theme state
val LocalThemeToggle = compositionLocalOf<() -> Unit> { {} }

// Language state
val LocalLanguageToggle = compositionLocalOf<() -> Unit> { {} }

@Composable
fun App() {
    // Theme state with localStorage persistence
    var themeMode by remember {
        mutableStateOf(
            when (LocalStorage.getItem(THEME_KEY)) {
                THEME_LIGHT -> ThemeMode.LIGHT
                else -> ThemeMode.DARK
            }
        )
    }

    // Language state with localStorage persistence
    var language by remember {
        mutableStateOf(
            when (LocalStorage.getItem(LANGUAGE_KEY)) {
                LANG_EN -> Language.EN
                else -> Language.ES
            }
        )
    }

    val toggleTheme: () -> Unit = {
        themeMode = when (themeMode) {
            ThemeMode.DARK -> {
                LocalStorage.setItem(THEME_KEY, THEME_LIGHT)
                ThemeMode.LIGHT
            }
            ThemeMode.LIGHT -> {
                LocalStorage.setItem(THEME_KEY, THEME_DARK)
                ThemeMode.DARK
            }
        }
        // Sync with HTML banner
        syncBannerTheme(themeMode)
    }

    val toggleLanguage: () -> Unit = {
        language = when (language) {
            Language.ES -> {
                LocalStorage.setItem(LANGUAGE_KEY, LANG_EN)
                Language.EN
            }
            Language.EN -> {
                LocalStorage.setItem(LANGUAGE_KEY, LANG_ES)
                Language.ES
            }
        }
        // Sync with HTML banner
        syncBannerLanguage(language)
    }

    CyberpunkTheme(themeMode = themeMode) {
        // Screen width state
        var screenWidth by remember { mutableStateOf(getWindowWidth()) }
        var viewportHeight by remember { mutableStateOf(getWindowHeight()) }
        val isMobile = screenWidth < 900

        // Scroll state
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        // Section positions for navigation (absolute positions within scroll content)
        val sectionAbsolutePositions = remember { mutableStateMapOf<String, Int>() }

        // Determine current section based on scroll position
        val currentSection by remember {
            derivedStateOf {
                val scrollY = scrollState.value
                val orderedSections = Section.entries.mapNotNull { section ->
                    sectionAbsolutePositions[section.id]?.let { pos -> section to pos }
                }.sortedBy { it.second }

                // Find the last section whose absolute position has been scrolled past
                // (with threshold of 1/3 viewport height for better UX)
                val threshold = scrollY + viewportHeight / 3
                orderedSections.lastOrNull { (_, pos) -> pos <= threshold }?.first
                    ?: Section.HEADER
            }
        }

        // Scroll to section function
        fun scrollToSection(section: Section) {
            sectionAbsolutePositions[section.id]?.let { position ->
                coroutineScope.launch {
                    scrollState.animateScrollTo(
                        position,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    )
                }
            }
        }

        // Helper to calculate absolute position from visual position
        fun updateSectionPosition(id: String, visualY: Int) {
            val absolutePos = visualY + scrollState.value
            sectionAbsolutePositions[id] = absolutePos
        }

        // Listen for window resize
        DisposableEffect(Unit) {
            val resizeListener: (Event) -> Unit = {
                screenWidth = getWindowWidth()
                viewportHeight = getWindowHeight()
            }
            window.addEventListener("resize", resizeListener)
            window.addEventListener("orientationchange", resizeListener)

            onDispose {
                window.removeEventListener("resize", resizeListener)
                window.removeEventListener("orientationchange", resizeListener)
            }
        }

        CompositionLocalProvider(
            LocalScreenWidth provides screenWidth,
            LocalThemeToggle provides toggleTheme,
            LocalLanguageToggle provides toggleLanguage,
            LocalLanguage provides language,
            LocalScrollPosition provides scrollState.value,
            LocalViewportHeight provides viewportHeight
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top banner
                ContactBanner(isMobile = isMobile)

                // Main content area
                Box(modifier = Modifier.fillMaxSize()) {
                    // Main scrollable content
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(CyberpunkThemeColors.background)
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    // Header - no animation (already has glitch/typewriter)
                    Box(
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            updateSectionPosition("header", coordinates.positionInRoot().y.toInt())
                        }
                    ) {
                        SectionHeader()
                    }

                    // Presentation with slide up animation
                    AnimatedSection(
                        animationType = AnimationType.SLIDE_UP,
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            updateSectionPosition("about", coordinates.positionInRoot().y.toInt())
                        }
                    ) {
                        SectionPresentation()
                    }

                    // Evolution - lazy loaded (below fold)
                    LazySection(minHeight = 600.dp) {
                        AnimatedSection(
                            animationType = AnimationType.SLIDE_UP,
                            delay = 100,
                            modifier = Modifier.onGloballyPositioned { coordinates ->
                                updateSectionPosition("career", coordinates.positionInRoot().y.toInt())
                            }
                        ) {
                            SectionEvolution()
                        }
                    }

                    // AI Projects - lazy loaded (below fold)
                    LazySection(minHeight = 800.dp) {
                        AnimatedSection(
                            animationType = AnimationType.SCALE_IN,
                            modifier = Modifier.onGloballyPositioned { coordinates ->
                                updateSectionPosition("projects", coordinates.positionInRoot().y.toInt())
                            }
                        ) {
                            SectionAIProjects()
                        }
                    }

                    // Skills - lazy loaded (below fold)
                    LazySection(minHeight = 500.dp) {
                        AnimatedSection(
                            animationType = AnimationType.SLIDE_UP,
                            modifier = Modifier.onGloballyPositioned { coordinates ->
                                updateSectionPosition("skills", coordinates.positionInRoot().y.toInt())
                            }
                        ) {
                            SectionSkills()
                        }
                    }

                    // Contact - lazy loaded (below fold)
                    LazySection(minHeight = 400.dp) {
                        AnimatedSection(
                            animationType = AnimationType.FADE_IN,
                            modifier = Modifier.onGloballyPositioned { coordinates ->
                                updateSectionPosition("contact", coordinates.positionInRoot().y.toInt())
                            }
                        ) {
                            SectionContact()
                        }
                    }
                }

                    // Fixed navigation menu at top
                    NavigationMenu(
                        currentSection = currentSection,
                        onSectionClick = { scrollToSection(it) },
                        isMobile = isMobile,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactBanner(isMobile: Boolean) {
    val strings = Strings.get()
    val borderColor = CyberpunkThemeColors.neonCyan.copy(alpha = 0.4f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CyberpunkThemeColors.surface)
            .drawBehind {
                // Bottom border
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
            .padding(horizontal = if (isMobile) 12.dp else 20.dp, vertical = if (isMobile) 8.dp else 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = strings.bannerText,
                style = MaterialTheme.typography.caption.copy(
                    fontSize = if (isMobile) 10.sp else 14.sp
                ),
                color = CyberpunkThemeColors.textPrimary
            )
            Text(
                text = strings.bannerEmail,
                style = MaterialTheme.typography.caption.copy(
                    fontSize = if (isMobile) 10.sp else 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = CyberpunkThemeColors.neonCyan,
                modifier = Modifier
                    .clickable {
                        window.open("mailto:alvaroquintanapalacios@gmail.com", "_blank")
                    }
                    .pointerHoverIcon(PointerIcon.Hand)
            )
        }
    }
}

// Sync theme with HTML banner, chat widget and project modal
private fun syncBannerTheme(themeMode: ThemeMode) {
    val theme = if (themeMode == ThemeMode.DARK) "dark" else "light"
    setDocumentTheme(theme)
    updateChatWidgetTheme(theme)
    updateProjectModalTheme(theme)
}

// Sync language with HTML banner, chat widget and project modal
private fun syncBannerLanguage(language: Language) {
    setDocumentLanguage(language.code)
    updateChatWidgetLang(language.code)
    updateProjectModalLang(language.code)
}

// Top-level JS interop functions for WASM compatibility
private fun setDocumentTheme(theme: String): Unit = js("document.documentElement.setAttribute('data-theme', theme)")
private fun setDocumentLanguage(lang: String): Unit = js("window.setPortfolioLanguage && window.setPortfolioLanguage(lang)")
private fun updateChatWidgetTheme(theme: String): Unit = js("window.updateChatWidgetTheme && window.updateChatWidgetTheme(theme)")
private fun updateChatWidgetLang(lang: String): Unit = js("window.updateChatWidgetLang && window.updateChatWidgetLang(lang)")
private fun updateProjectModalTheme(theme: String): Unit = js("window.updateProjectModalTheme && window.updateProjectModalTheme(theme)")
private fun updateProjectModalLang(lang: String): Unit = js("window.updateProjectModalLang && window.updateProjectModalLang(lang)")

// Obtener el ancho de ventana del navegador
private fun getWindowWidth(): Int {
    return window.innerWidth
}

// Obtener el alto de ventana del navegador
private fun getWindowHeight(): Int {
    return window.innerHeight
}
