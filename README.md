# Fast Hoppers (Fabric, Minecraft 1.21.8)

Makes hoppers transfer **up to 24 items every tick** with no cooldown, instead
of the vanilla 1 item per 8 ticks. It reuses Minecraft's own transfer logic, so
filters, item sorting and locked slots all still behave normally — there's just
a lot more of it, a lot faster.

To change the speed, edit `ITEMS_PER_TICK` in
`src/main/java/com/example/fasthoppers/FastHoppersMod.java` and rebuild.

## Build it (no installs needed) — GitHub
1. Make a new repository on GitHub.
2. Create the build file first so it can't get skipped: **Add file → Create new
   file**, name it `.github/workflows/build.yml`, and paste the contents of the
   `build.yml` in this project. Commit.
3. **Add file → Upload files** and drag in everything else from this folder
   (`build.gradle`, `gradle.properties`, `settings.gradle`, `src`, `gradle`).
   Commit.
4. Open the **Actions** tab, wait for the green check (~2 min), open the run,
   and download the **fasthoppers-mod** artifact from the bottom.
5. Inside is `fasthoppers-1.0.0.jar` (not the `-sources` one) — drop it in your
   `mods/` folder next to Fabric Loader.

## Heads-up
24 items/tick is very fast (~480 items/second). If it's too aggressive or laggy,
lower `ITEMS_PER_TICK` and rebuild.

## If the build goes red
The three hopper method names this hooks into (`serverTick`,
`insertAndExtract`, `transferCooldown`) are confirmed for 1.21.x but can shift
between versions. If the build complains about one of them, the names live in:
- `HopperBlockEntityMixin.java`     → `"serverTick"`
- `HopperBlockEntityInvoker.java`   → `@Invoker("insertAndExtract")`
- `HopperBlockEntityAccessor.java`  → `@Accessor("transferCooldown")`
Copy the error to me and I'll correct them.
