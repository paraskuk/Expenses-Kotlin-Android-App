// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    //id("com.google.devtools.ksp") version "1.9.24-1.0.20"
    //id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false

}