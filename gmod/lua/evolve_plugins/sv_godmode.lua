/*-------------------------------------------------------------------------------------------------------------------------
	Enable godmode for a player
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Godmode"
PLUGIN.Description = "Enable godmode for a player."
PLUGIN.Author = "Overv"
PLUGIN.ChatCommand = "god"
PLUGIN.Usage = "[players] [1/0]"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		local pls = evolve:findPlayer( args, ply )
		if ( #pls > 0 and !pls[1]:IsValid( ) ) then pls = { } end
		
		for _, pl in pairs( pls ) do
			if ( pl.EV_GodMode ) then
				pl:GodEnable( )
				pl.EV_GodMode = true
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has enabled godmode for ", evolve.colors.red, pl:Nick(), evolve.colors.white, "." )
			else
				pl:GodDisable( )
				pl.EV_GodMode = false
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has disabled godmode for ", evolve.colors.red, pl:Nick(), evolve.colors.white, "." )
			end
			
		end
		
		if ( #pls == 0 ) then
			evolve:notify( ply, evolve.colors.red, "No matching players found." )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

function PLUGIN:PlayerSpawn( ply )
	if ( ply.EV_GodMode ) then ply:GodEnable( ) end
end

evolve:registerPlugin( PLUGIN )