# Developing Info

Table of Contents:

-   [Getting Started](#getting-started)
-   [Coding Guidelines](#coding-guidelines)

## Getting Started

JDK 8 and git is required.

Fork the repository. git clone your fork, run the setupDecompWorkspace gradle task, run runClient task to run the client
and build task to build the jar.

If setupDecompWorkspace doesn't work, change `-Xmx1G` on the last line of `gradle.properties` to `-Xmx2G`. After running
setupDecompWorkspace undo the change.

You can also run setupDevWorkspace instead of setupDecompWorkspace but you will not have minecraft de-compiled.

If you use IntelliJ, run genIntelliRuns task and restart IntelliJ

## Coding Guidelines

-   Please use `this.` instead of just accessing the property.
-   When dealing with subclasses use the top level class to access static properties instead of nothing. Example:
    instead of `get()` use `Feature.get()`
-   Please comment most methods/properties so people know what its for
-   The IntelliJ IDEA formatter is recommended but not needed as all code is regularly formatted.
