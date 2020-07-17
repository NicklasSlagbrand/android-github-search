import org.codehaus.groovy.runtime.IOGroovyMethods
import org.codehaus.groovy.runtime.ProcessGroovyMethods
import java.io.BufferedReader
import java.io.InputStreamReader

val appVersionCode = System.getenv("BITRISE_BUILD_NUMBER")?.toInt() ?: 1
val appVersionName = "1.0.0"

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("gnag")
    id("realm-android")
}

androidExtensions {
    isExperimental = true
}

val ktlint: Configuration by configurations.creating

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.nicklasslagbrand.baseline"

        minSdkVersion(21)
        targetSdkVersion(29)

        multiDexEnabled = true

        versionCode = appVersionCode
        versionName = appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        compileOptions {
            setSourceCompatibility(1.8)
            setTargetCompatibility(1.8)
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("./keys/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        getByName("debug") {
            storeFile = file("./keys/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules-debug.pro"
            )
        }
    }

    flavorDimensions("main")

    productFlavors {
        create("dev") {
            setDimension("main")
            versionNameSuffix = ".dev"

            buildConfigField(
                "String",
                "API_BASE_URL",
                "\"https://api.github.com\""
            )
        }
    }
}

dependencies {
    val kotlinVersion = rootProject.extra.get("kotlinVersion")
    val lifecycleVersion = "2.2.0"
    val koinVersion = "2.0.1"
    val coroutinesVersion = "1.3.3"
    val glideVersion = "4.11.0"
    val navVersion = "2.3.0-alpha04"


    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.github.blackbeared:fusion:1.0.3")
    implementation ("androidx.preference:preference-ktx:1.1.1")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    implementation("jp.wasabeef:glide-transformations:4.1.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.jakewharton.timber:timber:4.7.1")
    implementation("org.koin:koin-android-viewmodel:$koinVersion")
    implementation("net.danlew:android.joda:2.10.3")

    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    // Kotlin
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation ("android.arch.paging:runtime:1.0.1")
    implementation ("android.arch.lifecycle:extensions:1.1.1")



    testImplementation("junit:junit:4.13")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.4.1")
    testImplementation("org.koin:koin-test:$koinVersion")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.amshove.kluent:kluent-android:1.60")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    androidTestImplementation("org.hamcrest:hamcrest-library:2.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")

    ktlint("com.pinterest:ktlint:${rootProject.extra.get("ktlint")}")
}

tasks {
    register<GradleBuild>("performCheckAndQuickBuild") {
        group = "Build"
        description =
            "Runs Unit tests and build DEBUG app variant (For that reason it builds fast)."
        tasks = listOf("clean", "updateLoco", "gnagCheck", "build")
    }

    register<GradleBuild>("performCheckAndFullBuild") {
        group = "Build"
        description = "Runs Unit tests and build full app with all variants."
        tasks = listOf("clean", "updateLoco", "gnagReport", "build")
    }


// if the build was triggered by the pull request then performs full build otherwise runs quick
// build
    register<GradleBuild>("performBuild") {
        description = "This build is designed to be triggered from CI"
        group = "Build"
        tasks = if (System.getenv("BITRISE_PULL_REQUEST") != null) {
            println("Building the app for the PR")
            listOf("performCheckAndFullBuild")
        } else {
            println("Building the app for the commit")
            listOf("performCheckAndQuickBuild")
        }
    }

    register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("src/**/*.kt")
        // to generate report in checkstyle format prepend following args:
        // "--reporter=plain", "--reporter=checkstyle,output=${buildDir}/ktlint.xml"
        // see https://github.com/shyiko/ktlint#usage for more
    }

    register<JavaExec>("ktlintFormat") {
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F", "src/**/*.kt")
    }
}
tasks.register<GradleBuild>("setVersionNameIntoEnv") {
    group = "CI related"
    description =
        "Creates env variable 'APP_VERSION_NAME' and sets its value to Application version limit. Should be run only on Bitrise CI."

    doFirst {
        println("Setting version limit env variable: "+"envman add --key APP_VERSION_NAME --value $appVersionName.$appVersionCode".exec())
    }
}

fun String.exec(): String {
    val process = Runtime.getRuntime().exec(this)

    val text: String = IOGroovyMethods.getText(
        BufferedReader(InputStreamReader(process.inputStream))
    )
    ProcessGroovyMethods.closeStreams(process)

    return text
}
/*
    Gnag is a Gradle plugin for Android projects that monitors
    and reports on common violations.
    Link: http://www.gnag.watch/
*/
gnag {
    isEnabled = true
    setFailOnError(true)

    ktlint {
        isEnabled = true
        toolVersion(rootProject.extra.get("ktlint") as String)
    }
    androidLint {
        isEnabled = true
        severity("Error")
    }
    detekt {
        isEnabled = true
        reporterConfig(project.file("detekt-config.yml"))
    }
}
