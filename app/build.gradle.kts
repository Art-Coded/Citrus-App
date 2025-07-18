plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-kapt") // REQUIRED for Hilt code generation
    id("dagger.hilt.android.plugin") // REQUIRED for Hilt to work
}

android {
    namespace = "com.example.citrusapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.citrusapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.foundation)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.accompanist.navigation.animation)

    implementation(libs.lottie.compose)

    implementation(libs.androidx.foundation.v160)

    implementation(libs.androidx.animation)

    implementation(libs.androidx.foundation.vversion)

    implementation (libs.accompanist.flowlayout)

    implementation (libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.firebase.firestore.ktx.v24103)

    implementation (libs.androidx.datastore.preferences)

    implementation(libs.coil.compose)

    implementation(libs.hilt.android)                       //implementation("com.google.dagger:hilt-android:2.51")
    kapt(libs.hilt.compiler)                                //kapt("com.google.dagger:hilt-compiler:2.51")
    implementation(libs.androidx.hilt.navigation.compose)   //implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

}