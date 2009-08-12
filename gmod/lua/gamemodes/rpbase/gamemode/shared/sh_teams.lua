------------------------------------
-- by SMILIE[AUT] 
------------------------------------

//RP:AddJob(String name, Color color, Table models, Number salary, Table weps, Table ammo, Number maxamount, Boolean needsvote)
// ammo ={ { "type1", amount1}, { "type2", amount2} } 
RP:AddJob("Citizen", Color(86, 131, 255, 255), RP.CivModels, 0, {"hands"}, {}, 100, false)
RP:AddJob("Cop", Color(48,119,32,255) , {"models/player/combine_soldier.mdl", "models/player/combine_soldier_prisonguard.mdl", "models/player/combine_super_soldier.mdl"}, 50, {"weapon_stunstick", "weapon_pistol", "hands"}, {{"Pistol", 50}}, 5, true)

//RP:AddEnt(String classname, Number prize, Table jobids, String nicename, String showmodel)
//if jobids table is empty then every class can buy this entity
RP:AddEnt("sent_ball", 50, {}, "Bouncy Ball", "models/Combine_Helicopter/helicopter_bomb01.mdl");

//RP:AddWeapon(String classname, Number prize, Table jobids, String nicename, String showmodel, Table ammo)
//if jobids table is empty then every class can buy this entity
// ammo ={ "type", amount} 
RP:AddWeapon("weapon_pistol", 30, {}, "Pistol", "models/weapons/W_pistol.mdl", {"pistol", 20});
RP:AddWeapon("weapon_deagle", 50, {}, "Deagle", "models/weapons/w_pist_deagle.mdl", {"pistol", 10});
RP:AddWeapon("weapon_fiveseven", 30, {}, "Fiveseven", "models/weapons/w_pist_fiveseven.mdl", {"pistol", 20});
RP:AddWeapon("weapon_glock", 30, {}, "Glock", "models/weapons/w_pist_glock18.mdl", {"pistol", 20});
RP:AddWeapon("weapon_smg1", 100, {2}, "SMG", "models/weapons/w_smg1.mdl", {"smg1", 30});
RP:AddWeapon("weapon_ak47", 100, {2}, "AK-47", "models/weapons/w_rif_ak47.mdl", {"smg1", 30});
RP:AddWeapon("weapon_m4", 100, {2}, "M4", "models/weapons/w_rif_m4a1.mdl", {"smg1", 30});
RP:AddWeapon("weapon_mp5", 100, {2}, "MP5", "models/weapons/w_smg_mp5.mdl", {"smg1", 30});
RP:AddWeapon("weapon_pumpshotgun", 100, {2}, "M3", "models/weapons/w_shot_m3super90.mdl", {"buckshot", 10});

//RP:AddAmmo(String ammotype, Number prize, Table jobids, String nicename, String showmodel, Number amount)
//if jobids table is empty then every class can buy this entity
// ammo ={ "type", amount} 
RP:AddAmmo("pistol", 50, {}, "Pistol Ammo", "models/Items/BoxSRounds.mdl", 50);
RP:AddAmmo("SMG1", 150, {2}, "SMG Ammo", "models/Items/BoxSRounds.mdl", 100);
RP:AddAmmo("SMG1_Grenade", 50, {2}, "SMG Grenade", "models/Items/AR2_Grenade.mdl", 10);
RP:AddAmmo("buckshot", 50, {2}, "Shotgun Ammo", "models/Items/BoxBuckshot.mdl", 25);


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