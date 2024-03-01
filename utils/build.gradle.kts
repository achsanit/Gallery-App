plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.achsanit.utils"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:model"))
    implementation(Deps.ANDROIDX_CORE)
    implementation(Deps.APPCOMPAT)
    testImplementation(Deps.JUNIT)
    androidTestImplementation(Deps.EXT_JUNIT)
    androidTestImplementation(Deps.ESPRESSO)
    implementation(Deps.RETROFIT)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)
}

kapt {
    correctErrorTypes = true
}