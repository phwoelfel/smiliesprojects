/*-------------------------------------------------------------------------------------------------------------------------
	Freeze a player
-------------------------------------------------------------------------------------------------------------------------*/

local PLUGIN = { }
PLUGIN.Title = "Freeze"
PLUGIN.Description = "Freeze a player."
PLUGIN.Author = "Overv"
PLUGIN.ChatCommand = "freeze"
PLUGIN.Usage = "[players] [1/0]"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		local pls = evolve:findPlayer( args, ply)
		if ( #pls > 0 and !pls[1]:IsValid( ) ) then pls = { } end
		
		for _, pl in pairs( pls ) do
			if ( !pl:GetNWBool("EV_Frozen") ) then
				pl:SetNWBool("EV_Frozen", true);
				pl:Lock();
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " has frozen ", evolve.colors.red, pl:Name(), evolve.colors.white, "." )
			else
				pl:UnLock();
				pl:SetNWBool("EV_Frozen", false);
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

function PLUGIN:Move( ply, data )
	return ply:GetNWBool( "EV_Frozen", false )
end

evolve:registerPlugin( PLUGIN )