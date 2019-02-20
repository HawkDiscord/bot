/*
 * Hawk - The next generation Discord moderation bot
 *
 * Copyright (C) 2019  Michael Rittmeister
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm").version("1.3.21")
    id("com.github.johnrengelman.shadow").version("4.0.3")
    java
    application
}

group = "cc.hawkbot"
version = "1.0-SNAPSHOT"

val kotlinVersion = "1.3.20"
val nodeVersion = "1.0.0"
val log4jVersion = "2.11.1"
val commonsCLIVersion = "1.4"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://dl.bintray.com/discordlist/maven/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8", kotlinVersion))

    // Logging
    compile("org.apache.logging.log4j:log4j-slf4j18-impl:$log4jVersion")
    compile("org.apache.logging.log4j:log4j-api:$log4jVersion")
    compile("org.apache.logging.log4j:log4j-core:$log4jVersion")

    // Util
    implementation("commons-cli:commons-cli:$commonsCLIVersion")

    // DLO
    implementation("org.discordlist.cloud:node:1.0.0")

    testCompile("junit", "junit", "4.12")
}

application {
    mainClassName = "cc.hawkbot.bot.BootstrapperKt"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks {
    "shadowJar"(ShadowJar::class) {
        baseName = project.name
        version = version
        archiveName = "$baseName.$extension"
    }
}