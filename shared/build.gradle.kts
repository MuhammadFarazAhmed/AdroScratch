plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin(KotlinPlugins.serialization) version "1.7.10"
    id(KotlinPlugins.parcelize)
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
        
        // export correct artifact to use all classes of library directly from Swift
        
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.13.1")
        }
        
        binaries.all {
            binaryOptions["memoryModel"] = "experimental"
        }
    }
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Koin) {
                    
                    implementation(koin)
                }
                with(Log) {
                    implementation(napier)
                }
                with(Moko) {
                    api(mokoMVVMCore)
                }
                with(Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(random)
                    implementation(clientSerialization)
                    implementation(contentNegotiation)
                    implementation(json)
                    implementation(auth)
                }
                with(Kotlinx) {
                    implementation(serializationCore)
                    implementation(datetime)
                }
                with(Coroutines) {
                    implementation(coroutines)
                }
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.21")

                implementation("io.jsonwebtoken:jjwt:0.9.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
//                implementation("clojure-interop:javax.crypto:1.0.2")
                //implementation("io.jsonwebtoken:jjwt:0.9.1")
                implementation(Ktor.clientAndroid)
                implementation(Koin.koinAndroid)
    
                with(Log) {
                    implementation(napier)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Ktor.clientIos)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.sharedcode"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    flavorDimensions("appType")
    productFlavors {
        create("devnode") {
            dimension = "appType"
        }
        create("qanode") {
            dimension = "appType"
        }
        create("uatnode") {
            dimension = "appType"
        }
        create("rcnode") {
            dimension = "appType"
        }
        create("productionnode") {
            dimension = "appType"
        }
    }
}