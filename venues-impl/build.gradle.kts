plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":venues"))

    // Moshi
    implementation(Libs.MOSHI)
    kapt(Libs.MOSHI_CODEGEN)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_CONVERTER_MOSHI)

    // Data Store
    implementation(Libs.DATA_STORE_PREFERENCES)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)

    // Unit tests
    testImplementation(Libs.JUNIT)

    // Instrumentation tests
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)
}
