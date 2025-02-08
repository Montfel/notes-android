plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.junit.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}