plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "1.9.22"

}

android {
    namespace = "com.example.mobilehelper"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mobilehelper"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //https://supabase.com/docs/reference/kotlin/installing
    //Supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:2.6.1"))
    //Для работы с БД
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    //Для авторизации
    implementation("io.github.jan-tennert.supabase:gotrue-kt")
    //Для получения картинок из хранилища данных
    implementation("io.github.jan-tennert.supabase:storage-kt")

    //Зависимость для того, чтобы возможно было отправлять запросы к supabase
    implementation("io.ktor:ktor-client-android:2.3.12")

    //Загрузка картинок
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Навигация
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

}