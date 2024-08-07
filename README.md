This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


## References

### Setup
- [Wizard](https://kmp.jetbrains.com/) - Kotlin Multiplatform Project Wizard
- [Tutorial](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)

### Data Fetching
- https://devgem.vercel.app/posts/introduction-to-kotlin-coroutines-for-fetching-data-from-multiple-tables
- https://medium.com/mobile-app-development-publication/kotlin-ktor-network-fetching-on-android-60e93e7f046d

### Architecture
- https://www.codetd.com/en/article/15853844
- https://medium.com/@adrianwitaszak/app-architecture-all-layers-explained-kotlin-multiplatform-shared-ui-series-a7333b1f622f
- https://dev.to/petitcl/introduction-to-functional-domain-driven-design-in-kotlin-5dg8
