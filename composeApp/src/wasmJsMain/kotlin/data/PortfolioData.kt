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
    val videoPlaceholder: Boolean = false,
    val demoUrl: String? = null
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
            subtitle = L("Plataforma de Gestión de Inversiones con Alertas Automatizadas", "Investment Management Platform with Automated Alerts"),
            description = L(
                "Plataforma full-stack que consolida inversiones de múltiples brokers, genera informes fiscales, analiza fundamentales con IA y envía alertas inteligentes diarias y semanales sobre precios objetivo y movimientos significativos del mercado. Arquitectura segura con Cloud Functions como middleware.",
                "Full-stack platform that consolidates investments from multiple brokers, generates tax reports, analyzes fundamentals with AI and sends intelligent daily and weekly alerts about target prices and significant market movements. Secure architecture with Cloud Functions as middleware."
            ),
            features = listOf(
                L("Alertas Automatizadas n8n", "Automated n8n Alerts"),
                L("Análisis Fundamental IA", "AI Fundamental Analysis"),
                L("Arquitectura Segura", "Secure Architecture"),
                L("Ratios de Valoración", "Valuation Ratios")
            ),
            techStack = listOf("Next.js 15", "Firebase", "Cloud Functions", "n8n", "Docker", "Gemini 2.0", "Perplexity AI", "TypeScript"),
            accentColor = CyberpunkColors.NeonYellow,
            githubUrl = null,
            isPrivate = true,
            isFeatured = true,
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Plataforma integral que consolida inversiones de múltiples brokers, genera informes fiscales y permite tomar decisiones basadas en datos.\n\n" +
                    "## Dashboard\n" +
                    "Visión global con índices mundiales, materias primas y sectores. Cada acción incluye ficha con fundamentales, gráficos históricos, ratios (PER, PEG, EV/EBITDA, P/B) y fórmulas automatizadas (DCF, Graham, Lynch) para detectar oportunidades.\n\n" +
                    "## Sistema de Alertas Inteligentes\n" +
                    "He desplegado un contenedor Docker con n8n en mi NAS Synology que ejecuta workflows automatizados diarios y semanales con dos frentes de análisis:\n" +
                    "› Alertas de Precio Objetivo: Compara los precios de alerta del usuario (Firebase) con cotizaciones en tiempo real, notificando cuando un valor alcanza su objetivo de compra o venta.\n" +
                    "› Análisis de Movimientos: Identifica las mayores subidas y bajadas de tu cartera, busca noticias relevantes con IA y analiza las posibles causas de cada movimiento.\n\n" +
                    "## Seguridad como Prioridad\n" +
                    "Toda comunicación con APIs externas pasa por Cloud Functions como middleware seguro: protege claves de API, valida peticiones y garantiza que datos sensibles nunca se exponen al cliente.\n\n" +
                    "## Análisis y Reporting\n" +
                    "Informes anuales con rentabilidades, mejores/peores valores y posiciones cerradas para declaración fiscal.",
                    "Comprehensive platform that consolidates investments from multiple brokers, generates tax reports and enables data-driven decisions.\n\n" +
                    "## Dashboard\n" +
                    "Global vision with world indices, commodities and sectors. Each stock includes fundamentals, historical charts, ratios (P/E, PEG, EV/EBITDA, P/B) and automated formulas (DCF, Graham, Lynch) to detect opportunities.\n\n" +
                    "## Intelligent Alert System\n" +
                    "I've deployed a Docker container with n8n on my Synology NAS running automated daily and weekly workflows with two analysis fronts:\n" +
                    "› Target Price Alerts: Compares user alert prices (Firebase) with real-time quotes, notifying when a stock reaches its buy or sell target.\n" +
                    "› Movement Analysis: Identifies the biggest gainers and losers in your portfolio, searches relevant news with AI and analyzes the causes behind each movement.\n\n" +
                    "## Security as Priority\n" +
                    "All external API communication goes through Cloud Functions as secure middleware: protects API keys, validates requests and ensures sensitive data is never exposed to the client.\n\n" +
                    "## Analysis and Reporting\n" +
                    "Annual reports with returns, best/worst performers and closed positions for tax filing."
                ),
                architecture = L(
                    "Frontend: Next.js 15 App Router + React Server Components\nUI: TailwindCSS + Shadcn/ui + TradingView Charts\nBackend: Firebase (Auth, Firestore, Cloud Functions como middleware)\nAutomatización: Docker + n8n en Synology NAS (alertas diarias/semanales)\nIA Análisis: Gemini 2.0 Flash (fundamentales + gráficos)\nIA Noticias: Perplexity AI (sonar-pro)\nSeguridad: Cloud Functions validan y protegen todas las APIs\nDespliegue: Vercel + Firebase",
                    "Frontend: Next.js 15 App Router + React Server Components\nUI: TailwindCSS + Shadcn/ui + TradingView Charts\nBackend: Firebase (Auth, Firestore, Cloud Functions as middleware)\nAutomation: Docker + n8n on Synology NAS (daily/weekly alerts)\nAI Analysis: Gemini 2.0 Flash (fundamentals + charts)\nAI News: Perplexity AI (sonar-pro)\nSecurity: Cloud Functions validate and protect all APIs\nDeployment: Vercel + Firebase"
                ),
                screenshots = emptyList(),
                keyHighlights = listOf(
                    L("Alertas automáticas: precios objetivo y análisis de movimientos vía n8n + Docker", "Automated alerts: target prices and movement analysis via n8n + Docker"),
                    L("Arquitectura segura: Cloud Functions como middleware protegiendo APIs", "Secure architecture: Cloud Functions as middleware protecting APIs"),
                    L("Notificaciones inteligentes diarias y semanales con análisis de causas", "Intelligent daily and weekly notifications with cause analysis"),
                    L("Informes fiscales anuales con rentabilidades y posiciones cerradas", "Annual tax reports with returns and closed positions"),
                    L("Visión global: índices mundiales, materias primas y sectores", "Global vision: world indices, commodities and sectors"),
                    L("Fórmulas de valoración automatizadas: DCF, Graham Number, Peter Lynch", "Automated valuation formulas: DCF, Graham Number, Peter Lynch"),
                    L("Dashboard multi-broker con P&L en tiempo real y multi-divisa", "Multi-broker dashboard with real-time P&L and multi-currency"),
                    L("IA busca noticias relevantes para explicar movimientos significativos", "AI searches relevant news to explain significant movements")
                ),
                videoPlaceholder = true
            )
        ),
        AIProject(
            title = "RAG Chatbot",
            subtitle = L("Asistente IA", "AI Assistant"),
            description = L(
                "Chatbot inteligente que responde preguntas sobre mi trayectoria profesional, habilidades técnicas y proyectos. Usa arquitectura RAG con embeddings vectoriales para ofrecer respuestas precisas y contextuales a reclutadores y empresas.",
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
                    "Este chatbot representa mi visión de cómo la IA puede transformar la experiencia de reclutamiento. En lugar de que los recruiters lean páginas de CV, pueden simplemente preguntar lo que necesitan saber. El sistema usa RAG (Retrieval-Augmented Generation) para buscar en mi base de conocimiento vectorizada y generar respuestas precisas y contextuales.",
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
                    L("Embeddings vectoriales para búsqueda semántica", "Vector embeddings for semantic search"),
                    L("Respuestas en español e inglés", "Responses in Spanish and English"),
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
                    "Desarrollé esta herramienta para resolver un problema real: convertir documentación técnica y libros a audio para escucharlos mientras conduzco o hago ejercicio. A diferencia de servicios cloud como ElevenLabs o Google TTS, todo el procesamiento ocurre localmente, garantizando privacidad total y sin costes recurrentes. El proyecto demuestra mi capacidad para integrar modelos de IA de última generación en aplicaciones de escritorio funcionales.",
                    "I developed this tool to solve a real problem: converting technical documentation and books to audio to listen while driving or exercising. Unlike cloud services like ElevenLabs or Google TTS, all processing happens locally, guaranteeing total privacy and no recurring costs. The project demonstrates my ability to integrate cutting-edge AI models into functional desktop applications."
                ),
                architecture = L(
                    "UI: Python + Tkinter (cross-platform)\nTTS Engine: Kokoro-82M (neural voices)\nTranslation: NLLB-200 (Meta AI)\nOptimización: ONNX Runtime para inferencia rápida\nFormatos: PDF, DOCX, TXT, EPUB",
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
                "Plataforma full-stack que integra agentes IA para análisis financiero. Combina búsqueda de noticias con análisis técnico de gráficos para inversores.",
                "Full-stack platform integrating AI agents for financial analysis. Combines news search with technical chart analysis for investors."
            ),
            features = listOf(
                L("Noticias IA en Tiempo Real", "Real-time News AI"),
                L("Análisis de Gráficos", "Chart Analysis"),
                L("Detección de Patrones", "Pattern Detection"),
                L("Indicadores Técnicos", "Technical Indicators")
            ),
            techStack = listOf("Next.js", "Perplexity AI", "Gemini 2.0", "TypeScript"),
            accentColor = CyberpunkColors.NeonGreen,
            githubUrl = "https://github.com/AlvaroQ/chart-analyzer-and-stock-news",
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Como inversor activo, necesitaba una herramienta que combinara análisis técnico con contexto de noticias en tiempo real. Este proyecto orquesta dos agentes IA: uno busca y resume noticias financieras relevantes usando Perplexity AI, mientras que otro analiza capturas de gráficos con Gemini 2.0 para identificar patrones técnicos, niveles de soporte/resistencia y señales de trading.",
                    "As an active investor, I needed a tool that combined technical analysis with real-time news context. This project orchestrates two AI agents: one searches and summarizes relevant financial news using Perplexity AI, while another analyzes chart screenshots with Gemini 2.0 to identify technical patterns, support/resistance levels, and trading signals."
                ),
                architecture = L(
                    "Frontend: Next.js 14 App Router + TailwindCSS\nNews Agent: Perplexity AI API (sonar-pro)\nChart Agent: Gemini 2.0 Flash (vision)\nOrquestación: API Routes con streaming\nDespliegue: Vercel Edge Functions",
                    "Frontend: Next.js 14 App Router + TailwindCSS\nNews Agent: Perplexity AI API (sonar-pro)\nChart Agent: Gemini 2.0 Flash (vision)\nOrchestration: API Routes with streaming\nDeployment: Vercel Edge Functions"
                ),
                screenshots = listOf("images/projects/chart-analyzer-and-stock-news.png"),
                keyHighlights = listOf(
                    L("Análisis multimodal: texto (noticias) + visión (gráficos)", "Multimodal analysis: text (news) + vision (charts)"),
                    L("Streaming de respuestas para UX fluida", "Response streaming for fluid UX"),
                    L("Detecta patrones: head & shoulders, triángulos, canales", "Detects patterns: head & shoulders, triangles, channels"),
                    L("Correlaciona noticias con movimientos de precio", "Correlates news with price movements"),
                    L("100% TypeScript con tipos estrictos", "100% TypeScript with strict types")
                ),
                demoUrl = "https://project-ia-three.vercel.app/"
            )
        ),
        AIProject(
            title = "Lotto Scan",
            subtitle = L("Escáner OCR con IA", "AI-Powered OCR Scanner"),
            description = L(
                "App Kotlin Multiplatform que escanea y gestiona boletos de lotería española usando OCR con IA. Procesa boletos offline con modelos locales.",
                "Kotlin Multiplatform app that scans and manages Spanish lottery tickets using AI-powered OCR. Process tickets offline with local models."
            ),
            features = listOf(
                L("Multi-Lotería", "Multi-Lottery Support"),
                L("OCR Offline", "Offline OCR"),
                L("Puntuación de Confianza", "Confidence Scoring"),
                L("Almacenamiento Local", "Local Storage")
            ),
            techStack = listOf("Kotlin", "Compose MP", "PaddleOCR", "ONNX", "SQLDelight"),
            accentColor = CyberpunkColors.Orchid,
            githubUrl = "https://github.com/AlvaroQ/lotto-scan",
            detailedInfo = ProjectDetails(
                longDescription = L(
                    "Este proyecto combina mi experiencia en Kotlin Multiplatform con IA on-device. La app escanea boletos de lotería española (Primitiva, Bonoloto, Euromillones, El Gordo) usando la cámara del dispositivo y extrae los números automáticamente con OCR. Todo el procesamiento ocurre localmente sin enviar imágenes a ningún servidor, garantizando privacidad y funcionamiento offline.",
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
                    L("KMP real: mismo código en Android y Desktop", "Real KMP: same code on Android and Desktop"),
                    L("OCR on-device con PaddleOCR optimizado", "On-device OCR with optimized PaddleOCR"),
                    L("Puntuación de confianza por cada número detectado", "Confidence score for each detected number"),
                    L("Historial de boletos con SQLDelight", "Ticket history with SQLDelight"),
                    L("Comprobación automática de premios", "Automatic prize checking")
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
