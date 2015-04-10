# Sparky: Java Edition!
The Java Edition of the [Sparky Engine](https://github.com/TheCherno/Sparky) by [TheCherno](https://github.com/TheCherno)

Based on [LWJGL 3](http://www.lwjgl.org/) and using [PNGDecoder](http://twl.l33tlabs.org/). Pieced together with code from Cherno's [live streams](http://www.twitch.tv/thecherno), [videos](https://www.youtube.com/playlist?list=PLlrATfBNZ98fqE45g3jZA_hLGUrD4bo6_) and [repository](https://github.com/TheCherno/Sparky) of his Sparky Engine series. Still capable of being optimized.

## Setup IDE Project
Setting up a project in the IDE of your choice is simple:
* [Download the source](https://github.com/floube/Sparky/archive/master.zip)
* Create a new project
* Copy and paste the downloaded folders
* Make sure that the `dependencies` and `src` folders are in the root project folder
* Open the Project Properties/Settings
* Add the `lwjgl.jar` (found in `dependecies/lwjgl/jar/`) as a Library/Dependency
* Add the `PNGDecoder.jar` (found in `dependecies/PNGDecoder/`) as a Library/Dependency
* Open the Run Configurations
* Add `-Djava.library.path="dependencies/lwjgl/native/"` to the VM Options
