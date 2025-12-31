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
    ORCHID,
    NEON_YELLOW
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
    ThemeColor.NEON_YELLOW -> CyberpunkThemeColors.neonYellow
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
 * Project details for featured projects with expanded modal
 */
data class ProjectDetails(
    val longDescription: i18n.LocalizedString,
    val architecture: i18n.LocalizedString,
    val screenshots: List<String>,
    val keyHighlights: List<i18n.LocalizedString>,
    val videoPlaceholder: Boolean = false
)

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
    val githubUrl: String? = null,
    val isLive: Boolean = false,
    val isPrivate: Boolean = false,
    val isFeatured: Boolean = false,
    val detailedInfo: ProjectDetails? = null
)

object AIProjectsData {
    private fun L(es: String, en: String) = i18n.LocalizedString(es, en)

    val projects = listOf(
        AIProject(
            title = "Portfolio & Stock",
            subtitle = L("Plataforma Integral de Gestion de Inversiones", "Comprehensive Investment Management Platform"),
            description = L(
                "Plataforma full-stack que consolida inversiones de multiples brokers, genera informes fiscales anuales, analiza fundamentales con IA y proporciona vision global de mercados con ratios de valoracion en tiempo real.",
                "Full-stack platform that consolidates investments from multiple brokers, generates annual tax reports, analyzes fundamentals with AI and provides global market vision with real-time valuation ratios."
            ),
            features = listOf(
                L("Informes Fiscales Anuales", "Annual Tax Reports"),
                L("Analisis Fundamental IA", "AI Fundamental Analysis"),
                L("Vision Global de Mercados", "Global Market Vision"),
                L("Ratios de Valoracion", "Valuation Ratios")
            ),
            techStack = listOf("Next.js 15", "Firebase", "Gemini 2.0", "Perplexity AI", "TypeScript", "TradingView"),
            accentColor = CyberpunkColors.NeonYellow,
            githubUrl = null,
            isPrivate = true,
            isFeatured = true,
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Portfolio & Stock es mi proyecto mas ambicioso: una plataforma integral de gestion de inversiones disenada para inversores serios que necesitan consolidar posiciones de multiples brokers, generar informes fiscales detallados y tomar decisiones informadas basadas en datos.\n\nEl sistema genera informes anuales completos con desglose de rentabilidades por periodo, identificacion de mejores y peores valores, y un registro exhaustivo de posiciones abiertas y cerradas durante el ejercicio - esencial para la declaracion fiscal.\n\nIncluye un area de vision global que muestra indices mundiales, materias primas y valores categorizados por sector y horizonte temporal, permitiendo identificar tendencias macro antes de invertir.\n\nAl navegar a cualquier accion individual, el usuario accede a una ficha completa con fundamentales de la empresa, graficos historicos interactivos, ratios de valoracion actuales e historicos (PER, PEG, EV/EBITDA, P/B), y formulas de valoracion automatizadas (DCF, Graham, Lynch) que identifican oportunidades de inversion infravaloradas.\n\nTodo presentado en una interfaz intuitiva y profesional que hace accesible el analisis financiero avanzado.",
                    "Portfolio & Stock is my most ambitious project: a comprehensive investment management platform designed for serious investors who need to consolidate positions from multiple brokers, generate detailed tax reports and make informed data-driven decisions.\n\nThe system generates complete annual reports with profitability breakdown by period, identification of best and worst performers, and an exhaustive record of positions opened and closed during the fiscal year - essential for tax filing.\n\nIt includes a global vision area showing world indices, commodities and stocks categorized by sector and time horizon, allowing identification of macro trends before investing.\n\nWhen navigating to any individual stock, the user accesses a complete profile with company fundamentals, interactive historical charts, current and historical valuation ratios (P/E, PEG, EV/EBITDA, P/B), and automated valuation formulas (DCF, Graham, Lynch) that identify undervalued investment opportunities.\n\nAll presented in an intuitive and professional interface that makes advanced financial analysis accessible."
                ),
                architecture = L(
                    "Frontend: Next.js 15 App Router + React Server Components\nUI: TailwindCSS + Shadcn/ui + TradingView Charts\nBackend: Firebase (Auth, Firestore, Cloud Functions)\nIA Analisis: Gemini 2.0 Flash (fundamentales + graficos)\nIA Noticias: Perplexity AI (sonar-pro)\nCache: Firebase + SWR para datos en tiempo real\nDespliegue: Vercel + Firebase",
                    "Frontend: Next.js 15 App Router + React Server Components\nUI: TailwindCSS + Shadcn/ui + TradingView Charts\nBackend: Firebase (Auth, Firestore, Cloud Functions)\nAI Analysis: Gemini 2.0 Flash (fundamentals + charts)\nAI News: Perplexity AI (sonar-pro)\nCache: Firebase + SWR for real-time data\nDeployment: Vercel + Firebase"
                ),
                screenshots = emptyList(),
                keyHighlights = listOf(
                    L("Informes fiscales anuales con rentabilidades, mejores/peores valores y posiciones cerradas", "Annual tax reports with returns, best/worst performers and closed positions"),
                    L("Vision global: indices mundiales, materias primas y sectores por horizonte temporal", "Global vision: world indices, commodities and sectors by time horizon"),
                    L("Ficha de accion con fundamentales, graficos historicos y ratios de valoracion", "Stock profile with fundamentals, historical charts and valuation ratios"),
                    L("Formulas de valoracion automatizadas: DCF, Graham Number, Peter Lynch", "Automated valuation formulas: DCF, Graham Number, Peter Lynch"),
                    L("Deteccion de oportunidades infravaloradas con scoring de inversion", "Undervalued opportunities detection with investment scoring"),
                    L("Dashboard multi-broker con P&L en tiempo real y multi-divisa", "Multi-broker dashboard with real-time P&L and multi-currency"),
                    L("Analisis tecnico con IA sobre capturas de graficos", "AI technical analysis on chart screenshots"),
                    L("Feed de noticias financieras personalizado por cartera", "Portfolio-personalized financial news feed")
                ),
                videoPlaceholder = true
            )
        ),
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
            isLive = true,
            isFeatured = true,
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Este chatbot representa mi vision de como la IA puede transformar la experiencia de reclutamiento. En lugar de que los recruiters lean paginas de CV, pueden simplemente preguntar lo que necesitan saber. El sistema usa RAG (Retrieval-Augmented Generation) para buscar en mi base de conocimiento vectorizada y generar respuestas precisas y contextuales.",
                    "This chatbot represents my vision of how AI can transform the recruitment experience. Instead of recruiters reading pages of CVs, they can simply ask what they need to know. The system uses RAG (Retrieval-Augmented Generation) to search my vectorized knowledge base and generate precise, contextual answers."
                ),
                architecture = L(
                    "Frontend: Widget JS embebido en portfolio\nBackend: Cloudflare Workers (Edge Computing)\nVector DB: Cloudflare Vectorize\nLLM: Llama 3.1 via Workers AI\nEmbeddings: BGE-base-en-v1.5\nDatos: Markdown con CV, proyectos y skills",
                    "Frontend: JS widget embedded in portfolio\nBackend: Cloudflare Workers (Edge Computing)\nVector DB: Cloudflare Vectorize\nLLM: Llama 3.1 via Workers AI\nEmbeddings: BGE-base-en-v1.5\nData: Markdown with CV, projects and skills"
                ),
                screenshots = listOf("images/projects/rag-chatbot.png"),
                keyHighlights = listOf(
                    L("Latencia <500ms gracias a edge computing", "Latency <500ms thanks to edge computing"),
                    L("Coste $0 - 100% en free tier de Cloudflare", "Cost $0 - 100% on Cloudflare free tier"),
                    L("Embeddings vectoriales para busqueda semantica", "Vector embeddings for semantic search"),
                    L("Respuestas en espanol e ingles", "Responses in Spanish and English"),
                    L("Integrado directamente en este portfolio", "Integrated directly into this portfolio")
                )
            )
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
            githubUrl = "https://github.com/AlvaroQ/TranslationAndVoiceLocally",
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Desarrolle esta herramienta para resolver un problema real: convertir documentacion tecnica y libros a audio para escucharlos mientras conduzco o hago ejercicio. A diferencia de servicios cloud como ElevenLabs o Google TTS, todo el procesamiento ocurre localmente, garantizando privacidad total y sin costes recurrentes. El proyecto demuestra mi capacidad para integrar modelos de IA de ultima generacion en aplicaciones de escritorio funcionales.",
                    "I developed this tool to solve a real problem: converting technical documentation and books to audio to listen while driving or exercising. Unlike cloud services like ElevenLabs or Google TTS, all processing happens locally, guaranteeing total privacy and no recurring costs. The project demonstrates my ability to integrate cutting-edge AI models into functional desktop applications."
                ),
                architecture = L(
                    "UI: Python + Tkinter (cross-platform)\nTTS Engine: Kokoro-82M (neural voices)\nTranslation: NLLB-200 (Meta AI)\nOptimizacion: ONNX Runtime para inferencia rapida\nFormatos: PDF, DOCX, TXT, EPUB",
                    "UI: Python + Tkinter (cross-platform)\nTTS Engine: Kokoro-82M (neural voices)\nTranslation: NLLB-200 (Meta AI)\nOptimization: ONNX Runtime for fast inference\nFormats: PDF, DOCX, TXT, EPUB"
                ),
                screenshots = listOf("images/projects/translation-and-voice-locally.jpg"),
                keyHighlights = listOf(
                    L("Voces neurales indistinguibles de humanos", "Neural voices indistinguishable from humans"),
                    L("Traduce entre 200+ idiomas antes de sintetizar", "Translates between 200+ languages before synthesis"),
                    L("Funciona 100% offline - sin internet requerido", "Works 100% offline - no internet required"),
                    L("Optimizado con ONNX para GPUs consumer", "Optimized with ONNX for consumer GPUs"),
                    L("Exporta a MP3, WAV, OGG con metadatos", "Exports to MP3, WAV, OGG with metadata")
                )
            )
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
            githubUrl = "https://github.com/AlvaroQ/chart-analyzer-and-stock-news",
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Como inversor activo, necesitaba una herramienta que combinara analisis tecnico con contexto de noticias en tiempo real. Este proyecto orquesta dos agentes IA: uno busca y resume noticias financieras relevantes usando Perplexity AI, mientras que otro analiza capturas de graficos con Gemini 2.0 para identificar patrones tecnicos, niveles de soporte/resistencia y senales de trading.",
                    "As an active investor, I needed a tool that combined technical analysis with real-time news context. This project orchestrates two AI agents: one searches and summarizes relevant financial news using Perplexity AI, while another analyzes chart screenshots with Gemini 2.0 to identify technical patterns, support/resistance levels, and trading signals."
                ),
                architecture = L(
                    "Frontend: Next.js 14 App Router + TailwindCSS\nNews Agent: Perplexity AI API (sonar-pro)\nChart Agent: Gemini 2.0 Flash (vision)\nOrquestacion: API Routes con streaming\nDespliegue: Vercel Edge Functions",
                    "Frontend: Next.js 14 App Router + TailwindCSS\nNews Agent: Perplexity AI API (sonar-pro)\nChart Agent: Gemini 2.0 Flash (vision)\nOrchestration: API Routes with streaming\nDeployment: Vercel Edge Functions"
                ),
                screenshots = listOf("images/projects/chart-analyzer-and-stock-news.png"),
                keyHighlights = listOf(
                    L("Analisis multimodal: texto (noticias) + vision (graficos)", "Multimodal analysis: text (news) + vision (charts)"),
                    L("Streaming de respuestas para UX fluida", "Response streaming for fluid UX"),
                    L("Detecta patrones: head & shoulders, triangulos, canales", "Detects patterns: head & shoulders, triangles, channels"),
                    L("Correlaciona noticias con movimientos de precio", "Correlates news with price movements"),
                    L("100% TypeScript con tipos estrictos", "100% TypeScript with strict types")
                )
            )
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
            githubUrl = "https://github.com/AlvaroQ/lotto-scan",
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Este proyecto combina mi experiencia en Kotlin Multiplatform con IA on-device. La app escanea boletos de loteria espanola (Primitiva, Bonoloto, Euromillones, El Gordo) usando la camara del dispositivo y extrae los numeros automaticamente con OCR. Todo el procesamiento ocurre localmente sin enviar imagenes a ningun servidor, garantizando privacidad y funcionamiento offline.",
                    "This project combines my expertise in Kotlin Multiplatform with on-device AI. The app scans Spanish lottery tickets (Primitiva, Bonoloto, Euromillions, El Gordo) using the device camera and automatically extracts numbers with OCR. All processing happens locally without sending images to any server, ensuring privacy and offline functionality."
                ),
                architecture = L(
                    "UI: Compose Multiplatform (Android + Desktop)\nOCR: PaddleOCR Lite optimizado para mobile\nInferencia: ONNX Runtime para cross-platform\nDB: SQLDelight (type-safe SQL)\nDI: Koin Multiplatform\nAsync: Kotlin Coroutines + Flow",
                    "UI: Compose Multiplatform (Android + Desktop)\nOCR: PaddleOCR Lite optimized for mobile\nInference: ONNX Runtime for cross-platform\nDB: SQLDelight (type-safe SQL)\nDI: Koin Multiplatform\nAsync: Kotlin Coroutines + Flow"
                ),
                screenshots = listOf(
                    "images/projects/lotto-scan-camera.jpg",
                    "images/projects/lotto-scan-preview.jpg"
                ),
                keyHighlights = listOf(
                    L("KMP real: mismo codigo en Android y Desktop", "Real KMP: same code on Android and Desktop"),
                    L("OCR on-device con PaddleOCR optimizado", "On-device OCR with optimized PaddleOCR"),
                    L("Puntuacion de confianza por cada numero detectado", "Confidence score for each detected number"),
                    L("Historial de boletos con SQLDelight", "Ticket history with SQLDelight"),
                    L("Comprobacion automatica de premios", "Automatic prize checking")
                )
            )
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
