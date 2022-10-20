plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
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
        podfile = project.file("../iosApp/Adro/Podfile")
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
                with(Moko) {
                    api(mokoMVVMCore)
                }
                with(Ktor) {

                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(clientSerialization)
                    implementation(contentNegotiation)
                    implementation(json)
                    implementation(auth)
                    implementation(random)
                }
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

                implementation(Ktor.clientAndroid)
                implementation(Koin.koinAndroid)

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
    flavorDimensions("appType")
    productFlavors {
        create("devnode"){
            dimension = "appType"
        }
        create("qanode"){
            dimension = "appType"
        }
        create("uatnode"){
            dimension = "appType"
        }
        create("rcnode"){
            dimension = "appType"
        }
        create("productionnode"){
            dimension = "appType"
        }
    }
}