/*-------------------------------------------------------------------------------------------------------------------------
	Run a lua command
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Run Lua"
PLUGIN.Description = "Run a lua command on the server."
PLUGIN.Author = "SMILIE"
PLUGIN.ChatCommand = "lua"
PLUGIN.Usage = nil

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsSuperAdmin( ) ) then
		if ( args ) then
			local cmd = table.concat(args, " ");
			//PrintTable(args);
			//print(cmd);
			evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " ran a lua command." )
			print(player.GetByUniqueID(ply:UniqueID()));
			RunString(cmd);//"rnr=" ..ply .." " .. || "local rnr = player.GetByUniqueID(" ..ply:UniqueID() ..") print(rnr) " ..
		else
			evolve:notify( ply, evolve.colors.red, "No arguments specified!" )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

evolve:registerPlugin( PLUGIN )