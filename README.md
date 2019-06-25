# ServerAdditionsUtil
A utility that allows for easy server-side-only additions to the game.  
Intended to be used as a jar-in-jar.

**This project is still very much a work in progress and as such nothing is final, everything can still undergo changes!**

### Usage
For basic usage, simply create new instances of or extend `ServerSideBlock` and `ServerSideBlockItem`. The blocks/items created will be able to be used by unmodded clients.

For more advanced usage, Blocks may implement the interface `ClientBlockStateProvider` and Items may implement the interface `ClientItemStackProvider`. This should have the same effect.

## Known issues
There are still many issues, but here are some that I am currently working on:
* Villager trades will not have the correct item displayed
* Picking up an item stack of a modded item in the creative inventory turns the item into what the client actually sees
* Falling block entities do not show the correct block state