------------------------------------
-- by SMILIE[AUT] & Decure
------------------------------------

//RP:AddJob(Number id, String name, Color color, Table models, Number salary, Table weps, Table ammo, Number maxamount, Boolean needsvote, Number jobneeded)
// ammo ={ { "type1", amount1}, { "type2", amount2} }  Note: if you give a default HL2 weapon the default ammo amount will also be given
// jobneeded is the id of the job which you have to be before changing
RP:AddJob(1, "Citizen", Color(0, 122, 8, 255), RP.CivModels, 30, {}, {}, 100, false, 0)
RP:AddJob(2, "Cook", Color(211 , 2, 65, 255) , {"models/player/mossman.mdl"}, 40, {}, {}, 3, false, 0)
RP:AddJob(3, "Barkeeper", Color(2, 211, 110, 255), {"models/player/mossman.mdl"},  35, {}, {}, 3, false, 0)
RP:AddJob(4, "Worker", Color(40, 40, 40, 255), {"models/player/combine_soldier.mdl"},  50, {}, {}, 3, false, 0)
RP:AddJob(5, "Medic", Color(17, 201, 211, 255), {"models/player/kleiner.mdl"},  65, {}, {}, 3, false, 0)
RP:AddJob(6, "Gundealer", Color(216, 153, 15, 255) , {"models/player/monk.mdl"}, 65, {}, {}, 3, true, 0)
RP:AddJob(7, "Gangster", Color(71, 47, 0, 255), RP.CivModels, 45, {}, {}, 4, false, 0)
RP:AddJob(8, "Gangsterboss", Color(0, 0, 0, 255), {"models/player/gman_high.mdl"}, 90, {}, {}, 1, false, 0)
RP:AddJob(9, "Lawyer", Color(138, 9, 181, 255), {"models/player/magnusson.mdl"},  100, {}, {}, 2, true, 0)
RP:AddJob(10, "Cop", Color(15, 85, 216, 255) , {"models/player/police.mdl", "models/player/combine_soldier.mdl", "models/player/combine_soldier_prisonguard.mdl"}, 75, {"weapon_stunstick", "weapon_pistol"}, {{"Pistol", 50}}, 4, true, 0)
RP:AddJob(11, "SWAT", Color(31, 6, 216, 255) , {"models/player/gasmask.mdl", "models/player/swat.mdl", "models/player/riot.mdl", "models/player/urban.mdl", "models/player/combine_super_soldier.mdl"}, 90, {"weapon_stunstick", "weapon_deagle", "weapon_m4"}, {{"Pistol", 50}, {"SMG1", 200}}, 4, true, 9)
RP:AddJob(12, "Mayor", Color(170, 34, 10, 255), {"models/player/breen.mdl"},  130, {}, {}, 1, true, 0)



//RP:AddEnt(Number id, String classname, Number prize, Table jobids, String nicename, String showmodel)
//if jobids table is empty then every class can buy this entity
RP:AddEnt(1, "sent_ball", 10, {}, "Bouncy Ball", "models/Combine_Helicopter/helicopter_bomb01.mdl");



//RP:AddWeapon(Number id, String classname, Number prize, Table jobids, String nicename, String showmodel, Table ammo)
//if jobids table is empty then every class can buy this weapon
// ammo ={ "type", amount}
RP:AddWeapon(1, "weapon_glock", 50, {6, 10, 11}, "Glock", "models/weapons/w_pist_glock18.mdl", {"pistol", 20});
RP:AddWeapon(2, "weapon_fiveseven", 50, {6, 10, 11}, "Five-Seven", "models/weapons/w_pist_fiveseven.mdl", {"pistol", 20});

RP:AddWeapon(3, "weapon_deagle", 50, {6, 10, 11}, "Deagle", "models/weapons/w_pist_deagle.mdl", {"pistol", 7});

RP:AddWeapon(4, "weapon_mac10", 100, {6, 11}, "UZI", "models/weapons/w_smg_mac10.mdl", {"smg1", 30});
RP:AddWeapon(5, "weapon_mp5", 100, {6, 11}, "MP - Navy", "models/weapons/w_smg_mp5.mdl", {"smg1", 30});
RP:AddWeapon(6, "weapon_ak47", 100, {6, 11}, "AK - 47", "models/weapons/w_rif_ak47.mdl", {"smg1", 30});
RP:AddWeapon(7, "weapon_m4", 100, {6, 11}, "M4A1", "models/weapons/w_rif_m4a1.mdl", {"smg1", 30});



//RP:AddAmmo(Number id, String ammotype, Number prize, Table jobids, String nicename, String showmodel, Number amount)
//if jobids table is empty then every class can buy this ammo
RP:AddAmmo(1, "pistol", 20, {}, "Pistol Ammo", "models/Items/357ammobox.mdl", 15);
RP:AddAmmo(2, "SMG1", 40, {}, "SMG Ammo", "models/Items/BoxSRounds.mdl", 30);



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