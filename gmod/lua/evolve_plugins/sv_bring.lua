/*-------------------------------------------------------------------------------------------------------------------------
	Bring a player to you
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Bring"
PLUGIN.Description = "Bring a player."
PLUGIN.Author = "Overv"
PLUGIN.ChatCommand = "bring"
PLUGIN.Usage = "[players]"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) and ply:IsValid( ) ) then	
		local pl = evolve:findPlayer( args, ply )
		
		for i, pl2 in pairs( pl ) do
			pl2:SetPos( ply:GetPos( ) + Vector( i * 60, 0, 0 ) )
		end
		
		if ( #pl > 0 ) then
			evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has brought ", evolve.colors.red, evolve:createPlayerList( pl ), evolve.colors.white, " to them." )
		else
			evolve:notify( ply, evolve.colors.red, "No matching players found." )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

evolve:registerPlugin( PLUGIN )