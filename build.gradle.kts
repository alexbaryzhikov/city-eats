import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application") version Versions.ANDROID_GRADLE_PLUGIN apply false
    id("com.android.library") version Versions.ANDROID_GRADLE_PLUGIN apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

task("runUnitTests") {
    dependsOn("common:testDebugUnitTest")
    dependsOn("common-impl:testDebugUnitTest")
    dependsOn("venues:testDebugUnitTest")
    dependsOn("venues-impl:testDebugUnitTest")
}

task("runAndroidTests") {
    dependsOn("common:connectedDebugAndroidTest")
    dependsOn("common-impl:connectedDebugAndroidTest")
    dependsOn("venues:connectedDebugAndroidTest")
    dependsOn("venues-impl:connectedDebugAndroidTest")
}
