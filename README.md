# ServerAdditionsUtil [![Latest version][version-image]][version-link]
A utility that allows for easy server-side-only additions to the game.

**This project is still very much a work in progress and as such nothing is final! Literally *everything* is still subject to major changes!**

## Maven
```gradle
repositories {
    maven { url "https://maven.extracraftx.com"}
}
dependencies {
    modCompile "com.extracraftx.minecraft:ServerAdditionsUtil:{VERSION}"
}
```
*Note: only versions `0.0.9` and above are available on this repository. For versions without sources/javadocs, [look here](https://github.com/ExtraCrafTX/ServerAdditionsUtil/blob/3b1d1bf553b05a7240dee1c70c4aec4d0293a017/README.md).*

## Usage
For basic usage, simply create new instances of or extend `ServerSideBlock`, `ServerSideBlockItem` and `ServerSideItem`. The blocks/items created will be able to be used by unmodded clients. The names and recipes of these blocks/items should be specified just like any other blocks/items.

For more advanced usage, Blocks may implement the interface `ClientBlockStateProvider` and Items may implement the interface `ClientItemStackProvider`. This should have the same effect.

You may also take a look at the `testing` branch of this repository to see an example implementation.

## Known issues
There are still many issues, but here are some that I already know of:
* Picking up an item stack of a modded item in the creative inventory turns the item into what the client actually sees (**WIP**)
* Just opening the creative inventory while in possesion of a modded block renders the block useless (**WIP**)
* Picking a block (middle click) in creative just gives you the vanilla version
* Using the added blocks/items in commands doesn't allow for auto-complete (**WIP**)
* Blocks with different strength to their representation don't have correct progress on client who's breaking block (**Partially solved, glitchy, probably impossible to fully solve**)

## Features being considered
* Allow blocks/items to be displayed without the vanilla variant on clients with the mod installed
* Support server resource packs for language and custom textures

[version-image]: https://badges.kaamiljasani.com/curseforge/latest/325342
[version-link]: https://maven.extracraftx.com/com/extracraftx/minecraft/ServerAdditionsUtil/