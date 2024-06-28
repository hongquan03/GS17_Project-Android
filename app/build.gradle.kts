plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.gs17"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gs17"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.google.android.material:material:1.4.0-beta01")
    implementation("com.github.bumptech.glide:glide:4.8.0")
    implementation("com.google.code.gson:gson:2.8.7")
    implementation ("com.google.android.material:material:<1.6.8>")

}