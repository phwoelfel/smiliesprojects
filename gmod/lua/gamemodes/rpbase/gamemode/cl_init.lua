------------------------------------
-- by SMILIE[AUT] 
------------------------------------

DeriveGamemode("sandbox");

function GM:Initialize()
	self.BaseClass:Initialize()
	GAMEMODE.ShowScoreboard = false
	// surface.CreateFont( string font_name, number size, number weight, boolean antialiasing, boolean italic, string new_font_name )
	surface.CreateFont("coolvetica", ScreenScale(20), 400, true, false, "rp_hudtext_large");
	surface.CreateFont("coolvetica", ScreenScale(10), 400, true, false, "rp_hudtext_small");
	surface.CreateFont("coolvetica", ScreenScale(8), 400, true, false, "rp_hudtext_verysmall");
	surface.CreateFont("Verdana", ScreenScale(6), 400, true, false, "rp_scbtext_small");
	surface.CreateFont("Verdana", ScreenScale(8), 700, true, false, "rp_scbhead");
	surface.CreateFont("Verdana", ScreenScale(20), 400, true, false, "rp_scbtext_large");
end

include("config.lua");
include("shared.lua");
include("client/cl_hudpickup.lua");
include("client/cl_gui.lua");
include("client/cl_hud.lua");
include("client/cl_gamemode.lua");
include("client/cl_concommands.lua");
include("client/cl_scoreboard.lua");
include("client/cl_usermessages.lua");
include("client/cl_menus.lua");
