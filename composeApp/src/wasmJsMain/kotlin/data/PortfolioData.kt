package data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import theme.CyberpunkColors
import theme.CyberpunkThemeColors
import personalpage.composeapp.generated.resources.Res
import personalpage.composeapp.generated.resources.logo_theranking
import personalpage.composeapp.generated.resources.logo_talento
import personalpage.composeapp.generated.resources.logo_santander_uk
import personalpage.composeapp.generated.resources.logo_bfy

/**
 * Theme-aware color identifiers for career nodes
 */
enum class ThemeColor {
    NEON_CYAN,
    NEON_GREEN,
    NEON_MAGENTA,
    NEON_PURPLE,
    SANTANDER_BLUE,
    ORCHID
}

/**
 * Resolve ThemeColor to actual Color based on current theme
 */
@Composable
fun ThemeColor.resolve(): Color = when (this) {
    ThemeColor.NEON_CYAN -> CyberpunkThemeColors.neonCyan
    ThemeColor.NEON_GREEN -> CyberpunkThemeColors.neonGreen
    ThemeColor.NEON_MAGENTA -> CyberpunkThemeColors.neonMagenta
    ThemeColor.NEON_PURPLE -> CyberpunkThemeColors.neonPurple
    ThemeColor.SANTANDER_BLUE -> CyberpunkThemeColors.santanderBlue
    ThemeColor.ORCHID -> CyberpunkThemeColors.orchid
}

/**
 * Centralized breakpoints for responsive design
 */
object Breakpoints {
    const val MOBILE = 900
    const val TABLET = 1200
    const val DESKTOP = 1400
    const val WIDE = 2000
}

/**
 * Extension functions for screen width checks
 */
fun Int.isMobile() = this < Breakpoints.MOBILE
fun Int.isTablet() = this in Breakpoints.MOBILE until Breakpoints.TABLET
fun Int.isDesktop() = this >= Breakpoints.TABLET

/**
 * Career timeline data
 */
data class CareerNode(
    val company: String,
    val period: String,
    val role: String,
    val description: String,
    val skills: List<String>,
    val themeColor: ThemeColor,
    val icon: String,
    val type: String,
    val logoRes: DrawableResource? = null
)

object CareerData {
    val timeline = listOf(
        CareerNode(
            company = "TheRanking",
            period = "2013 - 2015",
            role = "Android & iOS Developer",
            description = "Cross-platform development with Titanium. Built startup apps with social integrations.",
            skills = listOf("Titanium", "Java", "REST APIs", "Git"),
            themeColor = ThemeColor.NEON_CYAN,
            icon = "TR",
            type = "STARTUP",
            logoRes = Res.drawable.logo_theranking
        ),
        CareerNode(
            company = "TalentoMOBILE",
            period = "2015 - 2017",
            role = "Senior Mobile Developer",
            description = "Santander Bank projects: biometric signature, face recognition, voice commands, NFC.",
            skills = listOf("Android", "Biometrics", "voice commands", "Security"),
            themeColor = ThemeColor.NEON_GREEN,
            icon = "TM",
            type = "CONSULTANT",
            logoRes = Res.drawable.logo_talento
        ),
        CareerNode(
            company = "Santander UK",
            period = "2017",
            role = "Senior Developer & Android Specialist",
            description = "On-site in Milton Keynes. Retail & Business apps with Kotlin, NFC, OCR, geolocation.",
            skills = listOf("Kotlin", "NFC", "OCR", "MVP"),
            themeColor = ThemeColor.SANTANDER_BLUE,
            icon = "UK",
            type = "CORPORATE",
            logoRes = Res.drawable.logo_santander_uk
        ),
        CareerNode(
            company = "B-FY",
            period = "2018 - Present",
            role = "App Development Director",
            description = "Passwordless IDaaS platform for identity protection against impersonation. Leading SDK development across Android, iOS and Desktop with KMP.",
            skills = listOf("KMP", "iOS", "Compose", "Ktor"),
            themeColor = ThemeColor.NEON_MAGENTA,
            icon = "BF",
            type = "STARTUP",
            logoRes = Res.drawable.logo_bfy
        ),
        CareerNode(
            company = "AI Specialist",
            period = "2024 - Present",
            role = "AI Agent Orchestrator",
            description = "Building intelligent systems with LLMs, autonomous agents, and AI-powered workflows.",
            skills = listOf("Claude", "LangChain", "MCP", "RAG"),
            themeColor = ThemeColor.ORCHID,
            icon = "AI",
            type = "SPECIALIST"
        )
    )
}

/**
 * AI Projects data
 */
data class AIProject(
    val title: String,
    val subtitle: i18n.LocalizedString,
    val description: i18n.LocalizedString,
    val features: List<i18n.LocalizedString>,
    val techStack: List<String>,
    val accentColor: Color,
    val githubUrl: String,
    val isLive: Boolean = false  // Shows LIVE badge and "Try it" button
)

object AIProjectsData {
    private fun L(es: String, en: String) = i18n.LocalizedString(es, en)

    val projects = listOf(
        AIProject(
            title = "RAG Chatbot",
            subtitle = L("Asistente IA", "AI Assistant"),
            description = L(
                "Chatbot inteligente que responde preguntas sobre mi trayectoria profesional, habilidades tecnicas y proyectos. Usa arquitectura RAG con embeddings vectoriales para ofrecer respuestas precisas y contextuales a reclutadores y empresas.",
                "Smart chatbot that answers questions about my career, technical skills and projects. Uses RAG architecture with vector embeddings to provide accurate, contextual responses to recruiters and companies."
            ),
            features = listOf(
                L("Arquitectura RAG", "RAG Architecture"),
                L("Embeddings Vectoriales", "Vector Embeddings"),
                L("100% Serverless", "100% Serverless"),
                L("Respuestas Contextuales", "Contextual Answers")
            ),
            techStack = listOf("Cloudflare Workers", "Vectorize", "Llama 3.1", "BGE Embeddings"),
            accentColor = CyberpunkColors.NeonMagenta,
            githubUrl = "https://github.com/AlvaroQ/portfolio-chatbot",
            isLive = true
        ),
        AIProject(
            title = "Translation & Voice AI",
            subtitle = L("100% Procesamiento Local", "100% Local Processing"),
            description = L(
                "App de escritorio que convierte documentos (PDF, Word, TXT) en audio de alta calidad usando modelos IA locales. Sin nube, sin costes, privacidad total.",
                "Desktop app that converts documents (PDF, Word, TXT) into high-quality speech using local AI models. No cloud, no costs, full privacy."
            ),
            features = listOf(
                L("50+ Voces Neurales", "50+ Neural Voices"),
                L("200+ Idiomas", "200+ Languages"),
                L("Procesamiento Offline", "Offline Processing"),
                L("Soporte Documentos", "Document Support")
            ),
            techStack = listOf("Python", "PyTorch", "ONNX", "Kokoro-82M", "NLLB-200"),
            accentColor = CyberpunkColors.NeonCyan,
            githubUrl = "https://github.com/AlvaroQ/TranslationAndVoiceLocally"
        ),
        AIProject(
            title = "Chart Analyzer",
            subtitle = L("Inteligencia de Mercado en Tiempo Real", "Real-time Market Intelligence"),
            description = L(
                "Plataforma full-stack que integra agentes IA para analisis financiero. Combina busqueda de noticias con analisis tecnico de graficos para inversores.",
                "Full-stack platform integrating AI agents for financial analysis. Combines news search with technical chart analysis for investors."
            ),
            features = listOf(
                L("Noticias IA en Tiempo Real", "Real-time News AI"),
                L("Analisis de Graficos", "Chart Analysis"),
                L("Deteccion de Patrones", "Pattern Detection"),
                L("Indicadores Tecnicos", "Technical Indicators")
            ),
            techStack = listOf("Next.js", "Perplexity AI", "Gemini 2.0", "TypeScript"),
            accentColor = CyberpunkColors.NeonGreen,
            githubUrl = "https://github.com/AlvaroQ/chart-analyzer-and-stock-news"
        ),
        AIProject(
            title = "Lotto Scan",
            subtitle = L("Escaner OCR con IA", "AI-Powered OCR Scanner"),
            description = L(
                "App Kotlin Multiplatform que escanea y gestiona boletos de loteria espanola usando OCR con IA. Procesa boletos offline con modelos locales.",
                "Kotlin Multiplatform app that scans and manages Spanish lottery tickets using AI-powered OCR. Process tickets offline with local models."
            ),
            features = listOf(
                L("Multi-Loteria", "Multi-Lottery Support"),
                L("OCR Offline", "Offline OCR"),
                L("Puntuacion de Confianza", "Confidence Scoring"),
                L("Almacenamiento Local", "Local Storage")
            ),
            techStack = listOf("Kotlin", "Compose MP", "PaddleOCR", "ONNX", "SQLDelight"),
            accentColor = CyberpunkColors.Orchid,
            githubUrl = "https://github.com/AlvaroQ/lotto-scan"
        )
    )
}

/**
 * Skills data
 */
data class Skill(
    val name: String,
    val level: Float
)

data class SkillCategory(
    val title: String,
    val themeColor: ThemeColor,
    val skills: List<Skill>
)

object SkillsData {
    val categories = listOf(
        SkillCategory(
            title = "Development",
            themeColor = ThemeColor.NEON_GREEN,
            skills = listOf(
                Skill("Kotlin", 0.95f),
                Skill("Android SDK", 0.92f),
                Skill("Jetpack Compose", 0.90f),
                Skill("KMP", 0.88f),
                Skill("HTML/JS/CSS", 0.85f),
                Skill("iOS / Swift", 0.70f)
            )
        ),
        SkillCategory(
            title = "AI / ML Orchestration",
            themeColor = ThemeColor.NEON_MAGENTA,
            skills = listOf(
                Skill("Claude / Anthropic", 0.90f),
                Skill("Prompt Engineering", 0.88f),
                Skill("LangChain", 0.85f),
                Skill("Cursor / Copilot", 0.83f),
                Skill("MCP Protocol", 0.81f),
                Skill("n8n", 0.75f)
            )
        ),
        SkillCategory(
            title = "Backend / Tools",
            themeColor = ThemeColor.NEON_CYAN,
            skills = listOf(
                Skill("REST / GraphQL", 0.92f),
                Skill("Git / CI-CD", 0.91f),
                Skill("Pen-testing", 0.89f),
                Skill("Firebase", 0.88f),
                Skill("Ktor", 0.82f),
                Skill("Domotics", 0.82f)
            )
        )
    )
}

/**
 * Contact information
 */
object ContactData {
    const val EMAIL = "alvaroquintanapalacios@gmail.com"
    const val LOCATION = "Madrid, Spain (Remote)"
    const val LINKEDIN_URL = "https://linkedin.com/in/alvaro-quintana-palacios"
    const val LINKEDIN_DISPLAY = "linkedin.com/in/alvaro-quintana-palacios"
    const val GITHUB_URL = "https://github.com/AlvaroQ"
    const val GITHUB_DISPLAY = "github.com/AlvaroQ"
}
