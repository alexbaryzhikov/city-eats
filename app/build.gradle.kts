plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.cityeats"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        setProperty("archivesBaseName", "city-eats-$versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":common-impl"))
    implementation(project(":venues"))
    implementation(project(":venues-impl"))

    implementation(Libs.CORE_KTX)
    implementation(Libs.APP_STARTUP)

    // UI
    implementation(Libs.ACTIVITY_KTX)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT_LAYOUT)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.MATERIAL)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)
    androidTestImplementation(Libs.HILT_TESTING)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Instrumentation tests
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)

    // Unit tests
    testImplementation(Libs.JUNIT)
    testImplementation(Libs.TRUTH)
}
