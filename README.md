[![Build Status](https://app.bitrise.io/app/bf9c0865b47ba968/status.svg?token=l386gc_TQlKbrInBnCi_gw)](https://app.bitrise.io/app/bf9c0865b47ba968/status.svg?token=l386gc_TQlKbrInBnCi_gw)

Android Kotlin Baseline
==================================

The goal of this project is to bring a relevant app solution with the minimum needed features that stick early 
to the market to foster a user-centered experience while using the app and solve significant pains
of users.

It is divided in the following sections, of which the first one (discussion forum) has the highest priority in
the order of the backlog:

* Discussion forum (+ connect + message) to foster community building by social interaction, exchange, advise and knowledge sharing. (Focus topic with highest priority)
Peer-to-peer knowledge sharing and exchange e.g. via posting questions for experts to answer, participating in dialogs, finding and following experts, using private questions via direct messaging.

* Content and news hub to always stay updated on trending and specialized topics with
minimum search effort.
App displays news feeds/timeline from internal and external websites or news portals, e.g. latest updates from Surgery Reference, news from Clinical Divisions, content from PubMed or Primal Pictures,
trending topics in real-time or “what’s new” articles.

* Navigator to provide easy and fast access to all relevant online services (personal shortcuts
and recommendations).

App displays an overview of relevant AO and medical services leading to the respective websites and
platforms (internal and external sources such as Surgery Reference, Clinical Divisions, video hub,
member directory, courses and events, PubMed etc.).

Useful links
-----------------

[Architecting Android](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/), [Architecting Android...The evolution](https://fernandocejas.com/2015/07/18/architecting-android-the-evolution/), [Architecting Android...Reloaded](https://fernandocejas.com/2018/05/07/architecting-android-reloaded/)

[Valtech Baseline (Java)](https://github.com/ValtechDK/android-baseline-project/)

[Confluence]() is used as a documentation holder

[JIRA]() is used as a tasks manager

[BitRise](https://app.bitrise.io/app/bf9c0865b47ba968#) is used as CI tool.

[BitRise](https://app.bitrise.io/app/bf9c0865b47ba968#) is used as a delivery service by default

The development process follows [GitFlow workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

Tests
-----------------

Unit tests could be found [here](https://github.com/ValtechDK/AO_App_Hackathon_Android/tree/develop/app/src/test)

Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check
 * `./gradlew installDebug` - Install the debug apk on the current connected device
 * `./gradlew test` - Run tests
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration)
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests
 * `./gradlew downloadLocaliseTranslations` - Download Localize translation


Code style
-----------

Here you can code styles: https://github.com/ValtechDK/valtech-android-guidelines

Versioning: https://github.com/ValtechDK/valtech-android-guidelines/blob/master/code_guidelines/version_number_sequence.md
