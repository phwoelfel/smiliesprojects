------------------------------------
-- by SMILIE[AUT] 
------------------------------------
RP = {};

RP.RP_NAME = "Base RP";
//RP.RP_TABLENAME = "rp_data";
RP.DEBUG = true;

function RP:dbgPrint(msg)
	if(RP.DEBUG)then
		print(msg);
	end
end

function RP:dbgPrintTable(tbl)
	if(RP.DEBUG)then
		PrintTable(tbl);
	end
end

RP.CivModels = {
	"models/player/Group01/Female_01.mdl",
	"models/player/Group01/Female_02.mdl",
	"models/player/Group01/Female_03.mdl",
	"models/player/Group01/Female_04.mdl",
	"models/player/Group01/Female_06.mdl",
	"models/player/Group01/Female_07.mdl",
	"models/player/Group01/Male_01.mdl",
	"models/player/Group01/male_02.mdl",
	"models/player/Group01/male_03.mdl",
	"models/player/Group01/Male_04.mdl",
	"models/player/Group01/Male_05.mdl",
	"models/player/Group01/male_06.mdl",
	"models/player/Group01/male_07.mdl",
	"models/player/Group01/male_08.mdl",
	"models/player/Group01/male_09.mdl",
	"models/player/Group03/Female_01.mdl",
	"models/player/Group03/Female_02.mdl",
	"models/player/Group03/Female_03.mdl",
	"models/player/Group03/Female_04.mdl",
	"models/player/Group03/Female_06.mdl",
	"models/player/Group03/Female_07.mdl",
	"models/player/Group03/Male_01.mdl",
	"models/player/Group03/male_02.mdl",
	"models/player/Group03/male_03.mdl",
	"models/player/Group03/Male_04.mdl",
	"models/player/Group03/Male_05.mdl",
	"models/player/Group03/male_06.mdl",
	"models/player/Group03/male_07.mdl",
	"models/player/Group03/male_08.mdl",
	"models/player/Group03/male_09.mdl",
}

RP.colors = {};
RP.colors.blue1 = Color(0, 0, 255, 255);
RP.colors.green1 = Color(0, 255, 0, 255);
RP.colors.red1 = Color(255, 0, 0, 255);
RP.colors.yellow1 = Color(255, 255, 0, 255);
RP.colors.black = Color(0, 0, 0, 255);
RP.colors.white = Color(255, 255, 255, 255);

RP.colors.blue = Color(86, 142, 255, 255);
RP.colors.darkblue = Color(0, 80, 165, 255);
RP.colors.green = Color(176, 255, 86, 255);
RP.colors.darkgreen = Color(19, 96, 0, 255);
RP.colors.red = Color(255, 111, 86, 255);
RP.colors.grey = Color(140, 140, 140, 255);
RP.colors.orange = Color(255, 187, 0, 255);
RP.colors.yellow = Color(255, 229, 0, 255);

RP.colors.hudblue = Color(86, 142, 255, 200);
RP.colors.hudred = Color(255, 111, 86, 200);
RP.colors.hudgrey = Color(140, 140, 140, 200);
RP.colors.hudorange = Color(255, 187, 0, 200);
RP.colors.huddarkblue = Color(0, 80, 165, 200);


//job and buy menus
RP.colors.jobheader = Color(0, 34, 102, 255);
RP.colors.jobpanel = Color(75, 84, 102, 255);
RP.colors.entheader = Color(21, 71, 10, 255);
RP.colors.entpanel = Color(38, 127, 19, 255);
RP.colors.wepheader = Color(135, 20, 0, 255);
RP.colors.weppanel = Color(173, 91, 74, 255);
RP.colors.ammoheader = Color(135, 20, 0, 255);
RP.colors.ammopanel = Color(173, 91, 74, 255);

//scoreboard
RP.colors.scbbg = Color(56, 37, 0, 200); //background
RP.colors.scbbgb = Color(22, 14, 0, 255); //background border
// Color(255, 170, 0, 200)
RP.colors.scbfg = Color(255, 170, 0, 255); //foreground
//Color(0, 85, 255, 255)
RP.colors.scbtxt = Color(174, 174, 174, 255); //text
//Color(0, 85, 255, 255)


RP.jobs = {};
RP.entities = {};
RP.weapons = {};
RP.ammo = {};


