plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.achsanit.galleryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.achsanit.galleryapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":utils"))

    implementation(Deps.ANDROIDX_CORE)
    implementation(Deps.APPCOMPAT)
    implementation(Deps.ACTIVITY)
    implementation(Deps.MATERIAL)

    implementation(Deps.LIVEDATA)
    implementation(Deps.VIEWMODEL)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation(Deps.JUNIT)
    androidTestImplementation(Deps.EXT_JUNIT)
    androidTestImplementation(Deps.ESPRESSO)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.GLIDE)

}

kapt {
    correctErrorTypes = true
}