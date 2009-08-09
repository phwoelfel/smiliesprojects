------------------------------------
-- by SMILIE[AUT] 
------------------------------------

DeriveGamemode("sandbox");

// surface.CreateFont( string font_name, number size, number weight, boolean antialiasing, boolean italic, string new_font_name )
surface.CreateFont("coolvetica", ScreenScale(20), 400, true, false, "rp_hudtext_large");
surface.CreateFont("coolvetica", ScreenScale(10), 400, true, false, "rp_hudtext_small");
surface.CreateFont("coolvetica", ScreenScale(7), 400, true, false, "rp_hudtext_verysmall");

include("config.lua");
include("shared.lua");
include("client/cl_gui.lua");
include("client/cl_hud.lua");
include("client/cl_gamemode.lua");

