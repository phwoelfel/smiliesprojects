/*-------------------------------------------------------------------------------------------------------------------------
	Freeze somebody
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Freeze"
PLUGIN.Description = "Freeze a player"
PLUGIN.Author = "SMILIE"
PLUGIN.ChatCommand = "freeze"
PLUGIN.Usage = "<players>"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		local pls = evolve:findPlayer( args, ply)
		if ( #pls > 0 and !pls[1]:IsValid( ) ) then pls = { } end
		local enabled = true
		
		for _, pl in pairs( pls ) do
			if ( !pl:GetNWBool("ev_frozen") ) then
				pl:Lock();
				pl:SetNWBool("ev_frozen", true);
				enabled = true;
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has frozen ", evolve.colors.red, pl:Name(), evolve.colors.white, "." )
			else
				pl:UnLock();
				pl:SetNWBool("ev_frozen", false);
				enabled = false
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has unfrozen ", evolve.colors.red, pl:Name(), evolve.colors.white, "." )
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
