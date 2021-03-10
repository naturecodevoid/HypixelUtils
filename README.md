<h1 align="center">
HypixelUtils

<br>

<a href="https://naturecodevoid.dev/downloads/?name=hypixelutils"><img align="center" alt="GitHub release (latest)" src="https://img.shields.io/github/v/release/naturecodevoiddev/HypixelUtils?label=latest%20release%20%28click%20to%20download%29&style=for-the-badge&logo=github"></a>
<br>
<a href="https://naturecodevoid.dev/downloads/?name=hypixelutils"><img align="center" alt="Total downloads" src="https://img.shields.io/github/downloads/naturecodevoiddev/HypixelUtils/total?style=for-the-badge&logo=github"></a>
<br>
<a href="https://travis-ci.com/github/naturecodevoiddev/HypixelUtils"><img align="center" alt="Travis CI" src="https://img.shields.io/travis/com/naturecodevoiddev/HypixelUtils?style=for-the-badge&logo=travis"></a>
<br>
<a href="https://discord.gg/"><img align="center" alt="Discord" src="https://img.shields.io/discord/0?logo=discord&style=for-the-badge&label=Discord"></a>

</h1>

<!-- [![GitHub release (latest by date)](https://img.shields.io/github/v/release/naturecodevoiddev/HypixelUtils?label=latest%20release%20%28click%20to%20download%29&style=for-the-badge)](https://naturecodevoid.dev/downloads.html?name=hypixelutils)
[![Travis-ci.com stable](https://img.shields.io/travis/com/naturecodevoiddev/HypixelUtils?style=for-the-badge)](https://travis-ci.com/github/naturecodevoiddev/HypixelUtils) -->

Minecraft 1.8.9 forge mod with random stuff.

Table of Contents:

-   [Features](#features)
-   [Building from source without git](#building-from-source-without-git)
-   [Building from source with git (recommended)](#building-from-source-with-git-recommended)
-   [Developing](#developing)
-   [Why this mod is safe](#why-this-mod-is-safe)

## Features

-   Chat Ping
-   Clock
-   Coin Tracker
-   CPS Display (with right CPS)
-   FPS Display
-   Easy to use GUI
-   100% open source and safe! (if you're worried, see [Why this mod is safe](#why-this-mod-is-safe))

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

Please see [DEVELOPERS.md](/DEVELOPERS.md)

## Why this mod is safe

You may be worried that this mod will steal stuff from your computer. Here's a few reasons why it's completely safe:

-   **Public releases of this mod will only be built by [Travis CI](https://travis-ci.com/)**, a free continuous
    integration service.

    -   This means that nothing can be hidden in the mod before release because the only code it gets is the code on
        GitHub.
    -   **The only place this mod will be released is on our
        [GitHub repository](https://github.com/naturecodevoiddev/HypixelUtils).** My website (naturecodevoid.dev) only
        pulls the latest release and gets the download URL.
    -   If you want to see the logs of each release, go to https://travis-ci.com/github/naturecodevoiddev/HypixelUtils.
    -   To be safe, please do not download the release if it's not made by the `naturecodevoid-bot` account.

-   This mod is just for fun and one of my first times modding Minecraft.

If you want announcements for mod updates, sneak peeks, and other things, you can join our
[discord](https://discord.gg/). You can also ask questions or contact the developers there.

If you're still worried, you can build the mod yourself with [this guide](#building-from-source-without-git) and look through the [source code](/src/main/java/dev/naturecodevoid/hypixelutils).
