plugins {
  id("java")
}

group = "me.soymako"
version = "1.0.0"

repositories {
  maven(url = "https://repo.papermc.io/repository/maven-public/"){
    name = "papermc"
  }
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:26.3.build.+")
}

java{
  toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

val copyPlugin by tasks.registering(Copy::class) {
    dependsOn(tasks.jar)

    from(tasks.jar)
    into("D:/Minecraft Servers/26.3 test/plugins")
}

tasks.build {
    finalizedBy(copyPlugin)
}
