// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion = "1.3.70"
    extra.set("kotlinVersion", kotlinVersion)
    extra.set("ktlint", "0.36.0")

    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("com.btkelly:gnag:2.4.1")
        classpath("com.appswithlove.loco:loco:0.1.4")
        classpath("io.realm:realm-gradle-plugin:6.0.2")
        classpath("com.google.gms:google-services:4.3.3")
        classpath("com.google.firebase:perf-plugin:1.3.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
