plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.jupiter)
}

android {
    namespace = "com.kyawlinnthant.codigo.one"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kyawlinnthant.codigo.one"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.kyawlinnthant.codigo.one.DbTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.androidx.core)
    implementation(libs.bundles.network)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.google.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.google.hilt.compiler)
    implementation(libs.serialization.json)
    implementation(libs.androidx.runner)

    androidTestImplementation(libs.google.hilt.test)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk)

    testImplementation(libs.mock.web.server)

    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.coroutines.test)

    testImplementation(libs.jupiter.api)
    androidTestImplementation(libs.jupiter.api)

    testRuntimeOnly(libs.jupiter.engine)
    androidTestRuntimeOnly(libs.jupiter.engine)
    
    testImplementation(libs.assertk)
    androidTestImplementation(libs.assertk)
}
