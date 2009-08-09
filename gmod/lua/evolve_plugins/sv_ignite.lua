/*-------------------------------------------------------------------------------------------------------------------------
	Ignite a player
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Ignite"
PLUGIN.Description = "Ignite a player."
PLUGIN.Author = "Overv"
PLUGIN.ChatCommand = "ignite"
PLUGIN.Usage = "[players] [1/0]"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		local pls = evolve:findPlayer( args, ply)
		if ( #pls > 0 and !pls[1]:IsValid( ) ) then pls = { } end
		
		for _, pl in pairs( pls ) do
			if ( pl:IsOnFire() ) then
				pl:Extinguish( )
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has extinguished ", evolve.colors.red, pl:Name(), evolve.colors.white, "." )
			else
				pl:Ignite( 99999, 1 )
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has ignited ", evolve.colors.red, pl:Name(), evolve.colors.white, "." )
			end
		end
		
		if ( #pls == 0 ) then
			evolve:notify( ply, evolve.colors.red, "No matching players found." )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

function PLUGIN:PlayerDeath( ply )
	if ( ply:IsOnFire( ) ) then
		ply:Extinguish( )
	end
end

function PLUGIN:Move( ply )
	if ( ply:IsOnFire( ) and ply:WaterLevel( ) == 3 ) then
		ply:Extinguish( )
		evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " extinguished himself by jumping into water." )
	end
end

evolve:registerPlugin( PLUGIN )