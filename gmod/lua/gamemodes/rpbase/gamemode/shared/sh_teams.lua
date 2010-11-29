------------------------------------
-- by SMILIE[AUT] & Decure
------------------------------------

//RP:AddJob(String name, Color color, Table models, Number salary, Table weps, Table ammo, Number maxamount, Boolean needsvote, Number jobneeded)
// ammo ={ { "type1", amount1}, { "type2", amount2} }  Note: if you give a default HL2 weapon the default ammo amount will also be given
// jobneeded is the id of the job which you have to be before changing
RP:AddJob("Citizen", Color(0, 122, 8, 255), RP.CivModels, 30, {}, {}, 100, false, 0)
RP:AddJob("Cook", Color(211 , 2, 65, 255) , {"models/player/mossman.mdl"}, 40, {}, {}, 3, false, 0)
RP:AddJob("Barkeeper", Color(2, 211, 110, 255), {"models/player/mossman.mdl"},  35, {}, {}, 3, false, 0)
RP:AddJob("Worker", Color(40, 40, 40, 255), {"models/player/combine_soldier.mdl"},  50, {}, {}, 3, false, 0)
RP:AddJob("Medic", Color(17, 201, 211, 255), {"models/player/kleiner.mdl"},  65, {}, {}, 3, false, 0)
RP:AddJob("Gundealer", Color(216, 153, 15, 255) , {"models/player/monk.mdl"}, 65, {}, {}, 3, true, 0)
RP:AddJob("Gangster", Color(71, 47, 0, 255), RP.CivModels, 45, {}, {}, 4, false, 0)
RP:AddJob("Gangsterboss", Color(0, 0, 0, 255), {"models/player/gman_high.mdl"}, 90, {}, {}, 1, false, 0)
RP:AddJob("Lawyer", Color(138, 9, 181, 255), {"models/player/magnusson.mdl"},  100, {}, {}, 2, true, 0)
RP:AddJob("Cop", Color(15, 85, 216, 255) , {"models/player/police.mdl", "models/player/combine_soldier.mdl", "models/player/combine_soldier_prisonguard.mdl"}, 75, {"weapon_stunstick", "weapon_pistol"}, {{"Pistol", 50}}, 4, true, 0)
RP:AddJob("SWAT", Color(31, 6, 216, 255) , {"models/player/gasmask.mdl", "models/player/swat.mdl", "models/player/riot.mdl", "models/player/urban.mdl", "models/player/combine_super_soldier.mdl"}, 90, {"weapon_stunstick", "weapon_deagle", "weapon_m4"}, {{"Pistol", 50}, {"SMG1", 200}}, 4, true, 9)
RP:AddJob("Mayor", Color(170, 34, 10, 255), {"models/player/breen.mdl"},  130, {}, {}, 1, true, 0)



//RP:AddEnt(String classname, Number prize, Table jobids, String nicename, String showmodel)
//if jobids table is empty then every class can buy this entity
RP:AddEnt("sent_ball", 10, {}, "Bouncy Ball", "models/Combine_Helicopter/helicopter_bomb01.mdl");



//RP:AddWeapon(String classname, Number prize, Table jobids, String nicename, String showmodel, Table ammo)
//if jobids table is empty then every class can buy this weapon
// ammo ={ "type", amount}
RP:AddWeapon("weapon_glock", 50, {7}, "Glock", "models/weapons/w_pist_glock18.mdl", {"pistol", 20});
RP:AddWeapon("weapon_fiveseven", 50, {7}, "Five-Seven", "models/weapons/w_pist_fiveseven.mdl", {"pistol", 20});

RP:AddWeapon("weapon_deagle", 50, {7}, "Deagle", "models/weapons/w_pist_deagle.mdl", {"pistol", 7});

RP:AddWeapon("weapon_mac10", 100, {7}, "UZI", "models/weapons/w_smg_mac10.mdl", {"smg1", 30});
RP:AddWeapon("weapon_mp5", 100, {7}, "MP - Navy", "models/weapons/w_smg_mp5.mdl", {"smg1", 30});
RP:AddWeapon("weapon_ak47", 100, {7}, "AK - 47", "models/weapons/w_rif_ak47.mdl", {"smg1", 30});
RP:AddWeapon("weapon_m4", 100, {7}, "M4A1", "models/weapons/w_rif_m4a1.mdl", {"smg1", 30});



//RP:AddAmmo(String ammotype, Number prize, Table jobids, String nicename, String showmodel, Number amount)
//if jobids table is empty then every class can buy this ammo
RP:AddAmmo("pistol", 20, {}, "Pistol Ammo", "models/Items/357ammobox.mdl", 15);
RP:AddAmmo("SMG1", 40, {}, "SMG Ammo", "models/Items/BoxSRounds.mdl", 30);



/*
Models:
CS:S
models/weapons/Shotgun_shell.mdl
models/weapons/shell.mdl
models/weapons/Rifleshell.mdl
HL2
models/Items/BoxSRounds.mdl
models/Items/BoxMRounds.mdl
models/Items/AR2_Grenade.mdl
models/Items/BoxBuckshot.mdl
models/Items/combine_rifle_cartridge01.mdl
models/Items/combine_rifle_ammo01.mdl
models/Items/357ammobox.mdl
models/Items/grenadeAmmo.mdl
models/Items/CrossbowRounds.mdl
models/Items/BoxFlares.mdl
models/Items/ammocrate_smg1.mdl
models/Items/ammoCrate_Rockets.mdl
models/Items/ammocrate_ar2.mdl
models/Items/ammocrate_grenade.mdl
models/Items/HealthKit.mdl
models/Items/item_item_crate.mdl
models/Items/Flare.mdl
Ammo types:
AR2 - Ammunition of the AR2/Pulse Rifle
AlyxGun
Pistol - Ammunition of the 9MM Pistol
SMG1 - Ammunition of the SMG/MP7
357 - Ammunition of the .357 Magnum
XBowBolt - Ammunition of the Crossbow
Buckshot - Ammunition of the Shotgun
RPG_Round - Ammunition of the RPG/Rocket Launcher
SMG1_Grenade - Ammunition for the SMG/MP7 grenade launcher (secondary fire)
SniperRound
SniperPenetratedRound
Grenade - Note you must be given the grenade weapon (e.g. pl:Give ("weapon_grenade")) before you can throw any grenades
Thumper - Ammunition cannot exceed 2
Gravity
Battery
GaussEnergy
CombineCannon
AirboatGun
StriderMinigun
HelicopterGun
AR2AltFire - Ammunition of the AR2/Pulse Rifle 'combine ball' (secondary fire)
slam - See Grenade
*/