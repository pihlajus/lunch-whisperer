import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

group = "fi.atkpihlainen.lunchWhisperer"
version = "0.1"

plugins {
  id("io.kotless") version "0.1.7-beta-4" apply true
  kotlin("jvm") version "1.3.72"
  application
}

repositories {
  jcenter()
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("io.kotless", "kotless-lang", "0.1.6")
  implementation("org.jsoup:jsoup:1.11.3")
  implementation("com.slack.api:slack-api-client:1.4.0")

  testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

kotless {
  config {
    bucket = "<your_bucket_name>"

    terraform {
      profile = "<your_terraform_profile>"
      region = "<your_aws_region>"
    }
  }
}

tasks.withType<KotlinJvmCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    languageVersion = "1.3"
    apiVersion = "1.3"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

application {
  applicationDefaultJvmArgs = listOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005")
}


