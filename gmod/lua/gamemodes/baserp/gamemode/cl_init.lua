------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

DeriveGamemode("sandbox");
include("config.lua");
include("shared.lua");
//
GM.colors = {};
GM.colors.blue = Color(86, 142, 255, 255);
GM.colors.darkblue = Color(0, 80, 165, 255);
GM.colors.green = Color(176, 255, 86, 255);
GM.colors.red = Color(255, 111, 86, 255);
GM.colors.grey = Color(140, 140, 140, 255);
GM.colors.orange = Color(255, 187, 0, 255);
GM.colors.yellow = Color(255, 229, 0, 255);
GM.colors.black = Color(0, 0, 0, 255);
GM.colors.white = Color(255, 255, 255, 255);
GM.colors.hudblue = Color(86, 142, 255, 200);
GM.colors.hudred = Color(255, 111, 86, 200);
GM.colors.hudgrey = Color(140, 140, 140, 200);
GM.colors.hudorange = Color(255, 187, 0, 200);
GM.colors.huddarkblue = Color(0, 80, 165, 200);

// surface.CreateFont( string font_name, number size, number weight, boolean antialiasing, boolean italic, string new_font_name )
surface.CreateFont("coolvetica", ScreenScale(20), 400, true, false, "rp_hudtext_large");
surface.CreateFont("coolvetica", ScreenScale(10), 400, true, false, "rp_hudtext_small");
surface.CreateFont("coolvetica", ScreenScale(7), 400, true, false, "rp_hudtext_verysmall");


local clIncFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/client/*.lua");
for _,f in pairs(clIncFiles) do
	print("[" ..GM.RP_NAME .."][Client] Included "..f);
	include("/client/"..f);
end



