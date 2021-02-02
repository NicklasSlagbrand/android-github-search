
Android Github Search app
==================================

App seperated in 2 modules, App & Core. 

App
Android application containng all presentation logic. Build using MVVM pattern & Koin as a lightweight injection library.

Core
Acting as module, provides data through repositories to the App module.


Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check
 * `./gradlew installDebug` - Install the debug apk on the current connected device
 * `./gradlew test` - Run tests
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration)
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests

