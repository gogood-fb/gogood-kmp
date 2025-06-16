This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

# Contributing
New work should be associated to a ticket in the [Project](https://github.com/orgs/InspiringApps/projects/25).
- Create a new branch from `main`. Ex: `feature/myFeature` or `bugfix/myFix`
- Submit a PR against `main` with your new branch
- 1 approval is required as well as automated checks (see below)
- Merge PR and delete branch

# Deployment
TODO

# Tools

## [Kover](https://github.com/Kotlin/kotlinx-kover)
Code coverage reporting.
Runs on: `PR`, `Deploy`
- 70% coverage is enforced on branches and lines for **non-ui** `commonMain` code.
- Generate HTML report: `./gradlew koverHtmlReport`

## [Detekt](https://github.com/detekt/detekt)
Static analysis.
Detects common errors and enforces conventions.
Runs on: `PR`, `Deploy`
- Additional [rules](https://mrmans0n.github.io/compose-rules/) added for Compose
- Configuration located [here](./composeApp/config/detekt.yml)
- Run manually: `./gradlew detekt`

## [Spotless](https://github.com/diffplug/spotless)
Style formatting
Runs on: `PR`, `Deploy`
- Config located [here](./.editorconfig)
- Check style: `./gradlew spotlessCheck`
- Apply style automatically: `./gradlew spotlessApply`