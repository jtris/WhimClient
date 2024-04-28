plugins {
    id("java")
}

group = "me.jtris"
version = ""

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libraries"))
    implementation(files(
        "libraries/com/spigot-1.8.8-R0.1-SNAPSHOT-latest.jar",
        "libraries/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar"))
}

sourceSets {
    main {
        java {
            srcDir("src/me/jtris")
        }
        resources {
            srcDir("src/me/resources")
        }
    }
}
