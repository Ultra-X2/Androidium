// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //id("com.android.application") version "8.1.4" apply false
    //id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
dependencies {
    //implementation(libs.androidx.appcompat)
    //implementation(libs.androidx.navigation.compose)
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    // ...
}

allprojects {
    repositories {
        //google()
        //mavenCentral()
        // Add any other necessary repositories here
    }
}