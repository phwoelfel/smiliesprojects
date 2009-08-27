------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function um_SpawnSent(um)
	local entname = um:ReadString();
	RunConsoleCommand("gm_spawnsent", entname);
end
usermessage.Hook("RP_spawnsent", um_SpawnSent);

function um_GiveSwep(um)
	local entname = um:ReadString();
	RunConsoleCommand("gm_giveswep", entname);
end
usermessage.Hook("RP_giveswep", um_GiveSwep);