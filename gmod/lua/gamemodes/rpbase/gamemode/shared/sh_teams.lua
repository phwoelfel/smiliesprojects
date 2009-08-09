------------------------------------
-- by SMILIE[AUT] 
------------------------------------

//GM:AddJob(String name, Color color, Table models, Number salary, Table weps, Table ammo, Number maxamount)
// ammo ={ { "type1", amount1}, { "type2", amount2} } 
GM:AddJob("Citizen", Color(86, 131, 255, 255), RP.CivModels, 0, {}, {}, 100)
GM:AddJob("Cop", Color(48,119,32,255) , {"models/player/combine_soldier.mdl", "models/player/combine_soldier_prisonguard.mdl", "models/player/combine_super_soldier.mdl"}, 50, {"weapon_stunstick", "weapon_pistol"}, {{"Pistol", 50}}, 5)