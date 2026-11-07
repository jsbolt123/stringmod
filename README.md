# String Command (Fabric 1.21.1)

Adds `/string`. Anyone can run it (no OP needed). It fills every empty slot in
the main inventory and hotbar with a stack of 64 string. Armor and off-hand
slots are not touched.

Requires Fabric Loader 0.16+, Fabric API, Java 21.

## Building
Upload this folder to a GitHub repo. The included workflow builds it
(Actions tab -> build -> download the `stringmod` artifact). Or locally with
Gradle 8.12+ and JDK 21: `gradle build` -> `build/libs/stringmod-1.0.0.jar`.

Put the jar in `mods/` on the server (and/or client for singleplayer).
