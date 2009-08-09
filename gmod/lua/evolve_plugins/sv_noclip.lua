/*-------------------------------------------------------------------------------------------------------------------------
	Noclip somebody
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Noclip"
PLUGIN.Description = "Noclip a player"
PLUGIN.Author = "SMILIE"
PLUGIN.ChatCommand = "noclip"
PLUGIN.Usage = "<players> [0/1]"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		local pls = evolve:findPlayer( args, ply )
		if ( #pls > 0 and !pls[1]:IsValid( ) ) then pls = { } end
		
		for _, pl in pairs( pls ) do
			if ( pl:GetMoveType()==MOVETYPE_WALK) then
				pl:SetMoveType(MOVETYPE_NOCLIP);
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has noclipped ", evolve.colors.red, pl:Nick(), evolve.colors.white, "." )
			else
				pl:SetMoveType(MOVETYPE_WALK)
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has unnoclipped ", evolve.colors.red, pl:Nick(), evolve.colors.white, "." )
			end
		end
		
		if ( #pls == 0 ) then
			evolve:notify( ply, evolve.colors.red, "No matching players found." )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

evolve:registerPlugin( PLUGIN )
