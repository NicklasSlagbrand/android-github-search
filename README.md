
Android Kotlin Baseline MVVM
==================================

The goal of this project is to act as a template for other projects

Useful links
-----------------

[Architecting Android](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/), [Architecting Android...The evolution](https://fernandocejas.com/2015/07/18/architecting-android-the-evolution/), [Architecting Android...Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)

The development process follows [GitFlow workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check
 * `./gradlew installDebug` - Install the debug apk on the current connected device
 * `./gradlew test` - Run tests
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration)
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests

