# SkyDome
A Minecraft mod that makes the sky be rendered spherically (as a skydome) instead of a skybox  
If you're wondering what the difference is, see https://gamedev.stackexchange.com/a/48265 and https://en.wikipedia.org/wiki/Skybox_(video_games)  
This also brings the possibility to use 360° images (meaning HDRI images, but minecraft doesn't support HDR) as the sky texture. ([example texture](https://commons.wikimedia.org/wiki/File:Afterglow_of_a_sunset.jpg))

## How to use
I do not recommend using this mod if you don't necessarily need it, since it will make your game's performance worse (not by much if you use low settings, but still little).
If you are using Optifine, it is highly recommended for you to turn off fog in the game for the best experience. (For 1.8.9 Optifine L5: ESC -> Options -> Video Settings -> Details -> Fog)
This mod may cause issues with shaders, and I have no idea how to fix it.

## Installation:  
Download the latest release from the [releases page](https://github.com/UserTeemu/SkyDome/releases) for the correct Minecraft version anbd mod loader. (or build the jar yourself if you know how)
Then just put the jar into your mods folder.  
The mod will automatically download [Sk1er LLC's ModCore](https://sk1er.club/modcore) (a required dependency) if you don't already have it.

## Setting the texture
You need to put the sky texture in a resource pack. The sky texture's location and name should be `assets/skydome/sky.png`.  
**Make sure the texture is a PNG file.**  
[Example resource pack (for Minecraft 1.8)](https://raw.githubusercontent.com/UserTeemu/SkyDome/master/example_resource_pack.zip)

## Changing settings
Run the `/skydome` command in-game, and you'll find a cool configuration GUI. 