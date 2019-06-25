This is the testing branch. It has a block implemented for examples sake and for testing.  
The block, when right clicked, heals the player for 1 heart. If the player is already fully healed, then it restores 1 hunger point. If the player is already full, then it does nothing.
* [`HealingBlock.java`](src/main/java/com/extracraftx/minecraft/serveradditionsutil/block/HealingBlock.java) contains the code for the block itself. This is mostly the same as you would normally make it. `randomDisplayTick` cannot be used as that is a method called on the client side and would never be called, so instead I schedule a tick every 5 in-game ticks which spawns particles. These particles are there so the block can be differentiated from a normal sponge block.
* [`ServerAdditionsUtil.java`](src/main/java/com/extracraftx/minecraft/serveradditionsutil/ServerAdditionsUtil.java) is the entry point and registers the block and block item.
* [`resources/data/serveradditionsutil/recipes`](src/main/resources/data/serveradditionsutil/recipes) contains the crafting recipe, same as it would normally be.
* [`resources/assets/serveradditionsutil/lang`](src/main/resources/assets/serveradditionsutil/lang) contains the lang file for the name of the block. This is also the same as it would normally be.

# ServerAdditionsUtil
A utility that allows for easy server-side-only additions to the game.  
Intended to be used as a jar-in-jar.

**This project is still very much a work in progress and as such nothing is final! Literally *everything* is still subject to major changes!**

### Usage
For basic usage, simply create new instances of or extend `ServerSideBlock`, `ServerSideBlockItem` and `ServerSideItem`. The blocks/items created will be able to be used by unmodded clients. The names and recipes of these blocks/items should be specified just like any other blocks/items.

For more advanced usage, Blocks may implement the interface `ClientBlockStateProvider` and Items may implement the interface `ClientItemStackProvider`. This should have the same effect.

## Known issues
There are still many issues, but here are some that I am currently working on:
* Villager trades will not have the correct item displayed
* Picking up an item stack of a modded item in the creative inventory turns the item into what the client actually sees
* Just opening the creative inventory while in possesion of a modded block renders the block useless
* Falling block entities do not show the correct block state