------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

local rpconvars = {
	{"rp_sqltable", "rp_data"}, // table in database
	{"rp_physgun", 0}, // allow physgun or not
	{"rp_toolgun", 0}, // allow toolgun or not
	{"rp_startmoney", 800} // money when you first spawn
};

for _,cv in pairs(rpconvars)do
	if(!ConVarExists(cv[1]))then CreateConVar(cv[1], cv[2], { FCVAR_REPLICATED, FCVAR_ARCHIVE }); end
end
