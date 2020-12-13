plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.4.21"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:1.10.3-jdk8")
    testImplementation("com.natpryce:hamkrest:1.8.0.1")

    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}

kotlin {
    explicitApi()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}