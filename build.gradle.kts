// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }


    dependencies {

        classpath(libs.google.services.v441)
    }
}

plugins {
    id("com.android.application") version "8.3.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}