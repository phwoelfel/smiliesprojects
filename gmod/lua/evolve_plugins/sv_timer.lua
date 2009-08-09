/*-------------------------------------------------------------------------------------------------------------------------
	Create a timer
-------------------------------------------------------------------------------------------------------------------------*/

local cursec = 0;

local PLUGIN = { }
PLUGIN.Title = "Timer"
PLUGIN.Description = "Create a timer that runs a command"
PLUGIN.Author = "SMILIE"
PLUGIN.ChatCommand = "timer"
PLUGIN.Usage = "<title> <seconds> <command>"

function PLUGIN:Call( ply, args )
	if ( ply:EV_IsAdmin( ) ) then
		if ( table.Count(args)>=3 ) then
			local title = args[1];
			table.remove(args, 1);
			local secs = args[1];
			if(secs<=50)then
				table.remove(args, 1);
				local cmd = args[1];
				table.remove(args, 1);
				local params = table.concat(args, " ");
				
				evolve:notify( evolve.colors.blue, ply:Nick( ), evolve.colors.white, " started the timer \"", evolve.colors.red, title, evolve.colors.white, "\"." )
				timer.Simple(secs, function() game.ConsoleCommand(cmd .." " ..params .."\n") end); //RunConsoleCommand(cmd, params)
				timer.Create(title, 1, secs, my_ev_timer, title, secs);
				cursec = 0;
			else
				evolve:notify( ply, evolve.colors.red, "Can't create a timer longer than 50 seconds!" )
			end
		else
			evolve:notify( ply, evolve.colors.red, "No Arguments specified! Usage: !timer <title> <seconds> <command>" )
		end
	else
		evolve:notify( ply, evolve.colors.red, evolve.constants.notallowed )
	end
end

evolve:registerPlugin( PLUGIN )


function my_ev_timer(title, secs)
	cursec = cursec+1;
	evolve:notify( evolve.colors.white, title ..": " ..cursec);
	if( cursec == secs )then cursec = 0 end
end