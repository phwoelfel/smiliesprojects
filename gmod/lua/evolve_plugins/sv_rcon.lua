/*-------------------------------------------------------------------------------------------------------------------------
	Run a rcon command
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Rcon"
PLUGIN.Description = "Run a command on the server."
PLUGIN.Author = "SMILIE"
PLUGIN.ChatCommand = "rcon"
PLUGIN.Usage = nil

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsSuperAdmin( ) ) then
		if ( args ) then
			local cmd = args[1];
			table.remove(args, 1);
			local params = table.concat(args, " ");
			evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " ran the command \"", evolve.colors.red, cmd , evolve.colors.white, "\"." )
			//RunConsoleCommand(cmd, args);
			game.ConsoleCommand(cmd .." " ..params .."\n");
		else
			evolve:notify( ply, evolve.colors.red, "No Arguments specified!" )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

evolve:registerPlugin( PLUGIN )