allprojects {
    group = "org.monkey"
    version = "0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    pluginManager.apply("java-library")
    pluginManager.apply("maven-publish")

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    dependencies {
        // BOM: nhớ bọc platform(...) trong implementation(...)
        add("implementation", platform("org.springframework.boot:spring-boot-dependencies:3.5.5"))

        add("implementation", "redis.clients:jedis:6.0.0")

        add("compileOnly", "org.projectlombok:lombok:1.18.38")
        add("annotationProcessor", "org.projectlombok:lombok:1.18.38")

        add("implementation", "org.springframework.boot:spring-boot-starter-web")
        add("implementation", "org.springframework.boot:spring-boot-starter-data-redis")
    }

    // publishing bằng Kotlin DSL
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("common") {
                from(components["java"])
            }
        }
        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/DuyTC1811/common")
                credentials {
                    username = findProperty("gpr.user") as String?
                    password = findProperty("gpr.key") as String?
                }
            }
        }
    }
}
