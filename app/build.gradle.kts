plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.paras_test_android.assignment_paras"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.paras_test_android.assignment_paras"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation("androidx.test:runner")
    //androidTestImplementation("androidx.test.espresso:espresso-contrib")
    //androidTestImplementation("androidx.test.espresso")
    //androidTestImplementation("androidx.test:rules")


    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    //despite what Google says adding the ktx solves the issue
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    //kapt ("androidx.room:room-compiler:$room_version")
   // Kotlin Extensions and Coroutines support for Room
    //implementation "androidx.room:room-ktx:$room_version"
   // Test helpers
   testImplementation("androidx.room:room-testing:$room_version")


}