package i18n

import androidx.compose.runtime.Composable

interface AppStrings {
    // Header
    val systemInitialized: String
    val name: String
    val subtitle: String
    val platforms: String
    val builtWith: String
    val usingKotlin: String

    // Presentation
    val hiImAlvaro: String
    val jobTitle: String
    val linkedIn: String
    val github: String
    val bioP1: String
    val bioP2: String
    val bioP3: String
    val bioP4: String
    val bioP5: String
    val yearsExperienceNum: String
    val yearsExperienceLabel: String
    val yearsLeadingNum: String
    val yearsLeadingLabel: String
    val platformsNum: String
    val platformsLabel: String
    val platformsList: String
    val builtWithKotlin: String
    val kotlinMultiplatformWasm: String

    // Evolution
    val careerTimeline: String
    val careerSubtitle: String
    val yearsInTechLabel: String
    val yearsAtBfyLabel: String
    val companiesLabel: String

    // AI Projects
    val latestAiProjects: String
    val showcasingProjects: String
    val keyFeatures: String
    val techStack: String
    val viewOnGithub: String
    val liveBadge: String
    val tryIt: String

    // Skills
    val skillsMatrix: String

    // Contact
    val connect: String
    val whoami: String
    val whoamiResult: String
    val catContact: String
    val emailLabel: String
    val locationLabel: String
    val catLinks: String
    val availableFor: String

    // Theme/Language
    val lightMode: String
    val darkMode: String

    // Banner
    val bannerText: String
    val bannerEmail: String

    // Accessibility
    val a11yThemeToggle: String
    val a11yThemeToggleDark: String
    val a11yThemeToggleLight: String
    val a11yLanguageToggle: String
    val a11yLanguageToggleEs: String
    val a11yLanguageToggleEn: String
    val a11yNavigation: String
    val a11yNavigateTo: String
    val a11yCurrentSection: String
    val a11yCopyEmail: String
    val a11yEmailCopied: String
    val a11yOpenLinkedIn: String
    val a11yOpenGitHub: String
    val a11yOpenProject: String
    val a11ySkillLevel: String
}

// Helper to get current strings
object Strings {
    @Composable
    fun get(): AppStrings {
        return when (LocalLanguage.current) {
            Language.ES -> StringsEs
            Language.EN -> StringsEn
        }
    }
}

// Localized string for data classes
data class LocalizedString(
    val es: String,
    val en: String
) {
    @Composable
    fun get(): String = when (LocalLanguage.current) {
        Language.ES -> es
        Language.EN -> en
    }
}
