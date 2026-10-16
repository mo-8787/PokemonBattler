# Pokédex

A simple Pokédex with basic functions that can initiate simple battles

## Description

This Pokédex has 10 different main functions.

Option 1 will show all available Pokémon with their corresponding 
attributes and attacks.

Option 2 will give you the option to register a new Pokémon to the
Pokédex.

Option 3 would usually give you the option to edit an existing Pokémon, 
but is for now unavailable due to some internal changes 
(changing some fields to final).

Option 4 will give you the option to
remove a registered Pokémon from the Pokédex.

Option 5 will save changes to a file/files.

Option 6 will upload from a file/files.

Option 7 will factory reset the Pokédex.

Option 8 will start a battle between Ash (You) and the CPU. 

Option 9 will show the win/loss history of Ash.

Option 10 will save changes and close Pokédex.




### Executing program

How to run the program:

The Pokédex is pretty simple to run and navigate in.

Although the user can no longer edit a Pokémon, it's now possible to 
do Pokémon battles by opting for option 8. 

A coin flip will decide which Pokémon gets to attack first. 
Once a Pokémon is fainted the battle is over.

The damage formula for this battle is determined by the base damage
of chosen attack that gets multiplied by a rng factor (0.85-1.0).
Each attack has a 1/24 (roughly 4%) chance to inflict critical damage
(1.5x multiplier).

The program uses Sleephelper.sleep to try and replicate the delay in classic
Pokémon battles between for example different attacks and their execution.






