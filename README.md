<h1 align="center">
HypixelUtils

<br>

<a href="https://naturecodevoid.dev/downloads/?name=hypixelutils"><img align="center" alt="GitHub release (latest)" src="https://img.shields.io/github/v/release/naturecodevoiddev/HypixelUtils?label=latest%20release%20%28click%20to%20download%29&style=for-the-badge"></a>
<br>
<a href="https://naturecodevoid.dev/downloads/?name=hypixelutils"><img alt="Total downloads" src="https://img.shields.io/github/downloads/naturecodevoiddev/HypixelUtils/total?style=for-the-badge"></a>
<br>
<a href="https://travis-ci.com/github/naturecodevoiddev/HypixelUtils"><img align="center" alt="Travis CI" src="https://img.shields.io/travis/com/naturecodevoiddev/HypixelUtils?style=for-the-badge"></a>

</h1>

<!-- [![GitHub release (latest by date)](https://img.shields.io/github/v/release/naturecodevoiddev/HypixelUtils?label=latest%20release%20%28click%20to%20download%29&style=for-the-badge)](https://naturecodevoid.dev/downloads.html?name=hypixelutils)
[![Travis-ci.com stable](https://img.shields.io/travis/com/naturecodevoiddev/HypixelUtils?style=for-the-badge)](https://travis-ci.com/github/naturecodevoiddev/HypixelUtils) -->

Minecraft mod with random stuff.

Table of Contents:

-   [Building from source without git](#building-from-source-without-git)
-   [Building from source with git (recommended)](#building-from-source-with-git-recommended)
-   [Developing](#developing)

## Building from source without git

JDK 8 is required.

Download the source code at https://github.com/naturecodevoiddev/HypixelUtils/archive/dev.zip. Unzip it and then run
this:

```sh
cd ./HypixelUtils-dev
./gradlew build
```

The jar will be in `HypixelUtils-dev/build/lib/HypixelUtils-<VERSION>.jar`

## Building from source with git (recommended)

JDK 8 and git is required.

Run this:

```sh
git clone https://github.com/naturecodevoiddev/HypixelUtils.git
cd ./HypixelUtils
./gradlew build
```

The jar will be in `HypixelUtils/build/lib/HypixelUtils-<VERSION>.jar`

If there's an update, and you want to rebuild, run this:

```sh
cd ./HypixelUtils
git fetch
git pull
./gradlew build
```

The jar will be in `HypixelUtils/build/lib/HypixelUtils-<VERSION>.jar`

## Developing

JDK 8 and git is required.

Fork the repository. git clone your fork, run the setupDecompWorkspace gradle task, run runClient task to run the client
and build task to build the jar.

If you use IntelliJ, run genIntelliRuns task and restart IntelliJ
