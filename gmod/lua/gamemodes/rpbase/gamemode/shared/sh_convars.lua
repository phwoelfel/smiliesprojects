------------------------------------
-- by SMILIE[AUT] 
------------------------------------

RP.convars = {
	{"rp_sqltable", "rp_data"}, // table in database
	{"rp_physgun", 0}, // allow physgun or not
	{"rp_toolgun", 0}, // allow toolgun or not
	{"rp_allowspawnprops", 1}, // allow prop spawning or not
	{"rp_allowspawnnpcs", 0}, // allow npc spawning or not
	{"rp_allowspawneffects", 1}, // allow effect spawning or not
	{"rp_allowspawnragdolls", 1}, // allow ragdoll spawning or not
	{"rp_allowspawnsents", 0}, // allow sent spawning or not
	{"rp_allowspawnsweps", 0}, // allow swep spawning or not
	{"rp_allowspawnvehicles", 1}, // allow vehicle spawning or not
	{"rp_noclip", 1}, // allow noclip or not
	{"rp_propcost", 10}, // how much a prop costs
	{"rp_effectscost", 10}, // how much a effect costs
	{"rp_defswepcost", 500}, // default cost for sweps
	{"rp_defsentcost", 200}, // default cost for sents
	{"rp_defammocost", 50}, // default cost for ammo
	{"rp_npccost", 200}, // how much a npc costs
	{"rp_vehiclecost", 100}, // how much a vehicle costs
	{"rp_doorcost", 20}, // how much a door costs
	{"rp_startmoney", 800} // money when you first spawn
};

for _,cv in pairs(RP.convars)do
	if(!ConVarExists(cv[1]))then
		CreateConVar(cv[1], cv[2], { FCVAR_REPLICATED, FCVAR_ARCHIVE });
		RP:dbgPrint("created convar " ..cv[1] .." with value " ..cv[2]);
	end
end
