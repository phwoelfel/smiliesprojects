------------------------------------
-- by SMILIE[AUT] 
------------------------------------

//RP:AddJob(String name, Color color, Table models, Number salary, Table weps, Table ammo, Number maxamount, Boolean needsvote)
// ammo ={ { "type1", amount1}, { "type2", amount2} } 
RP:AddJob("Citizen", Color(86, 131, 255, 255), RP.CivModels, 0, {}, {}, 100, false)
RP:AddJob("Cop", Color(48,119,32,255) , {"models/player/combine_soldier.mdl", "models/player/combine_soldier_prisonguard.mdl", "models/player/combine_super_soldier.mdl"}, 50, {"weapon_stunstick", "weapon_pistol"}, {{"Pistol", 50}}, 5, true)

//RP:AddEnt(String classname, Number prize, Number jobid, String nicename, String showmodel)
RP:AddEnt("sent_ball", 50, 1, "Bouncy Ball", "models/Combine_Helicopter/helicopter_bomb01.mdl");