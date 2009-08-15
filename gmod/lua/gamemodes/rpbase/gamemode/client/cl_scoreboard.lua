------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function GM:ScoreboardShow()
	GAMEMODE.ShowScoreboard = true
end


function GM:ScoreboardHide()
	GAMEMODE.ShowScoreboard = false
end

function GM:HUDDrawScoreBoard()
	if (!GAMEMODE.ShowScoreboard) then return end
	
	local scbw = 800;
	local scbh = 150 + #player.GetAll()*35;
	local scbx = ScrW()/2 - scbw/2;
	local scby = ScrH()/2 - scbh/2;
	
	/*
	RP:dbgPrint("scbw: " ..scbw);
	RP:dbgPrint("scbh: " ..scbh);
	RP:dbgPrint("scbx: " ..scbx);
	RP:dbgPrint("scby: " ..scby);
	*/
	local plytext = {};
	plytext.y = scby+115;
	plytext.heady = scbx+80;
	plytext.rpnamex = scbx+20;
	plytext.namex = scbx+120;
	plytext.jobx = scbx+220;
	plytext.deathx = scbx+scbw-50;
	plytext.killx = plytext.deathx-40;
	plytext.pingx = plytext.killx-50;
	 
	
	local scbinfo = {};
	scbinfo.bordersize = 20;
	scbinfo.color = RP.colors.scbbg;
	scbinfo.w = scbw;
	scbinfo.h = scbh;
	scbinfo.x = scbx;
	scbinfo.y = scby;
	
	//draw.RoundedBox( Number Bordersize, Number X, Number Y, Number Width, Number Height, Table Color )
	//draw.SimpleText( String Text, String Font, Number X, Number Y, Table Colour, Number Xalign, Number Yalign )
	draw.RoundedBox(scbinfo.bordersize, scbinfo.x-5, scbinfo.y-5, scbinfo.w+10, scbinfo.h+10, RP.colors.scbbgb);
	draw.RoundedBox(scbinfo.bordersize, scbinfo.x, scbinfo.y, scbinfo.w, scbinfo.h, scbinfo.color);
	draw.SimpleText(GAMEMODE.Name .. " - " .. GAMEMODE.Author, "rp_scbtext_large", scbx+scbw/2, scby+20, RP.colors.scbtxt, TEXT_ALIGN_CENTER, TEXT_ALIGN_TOP);
	
	draw.SimpleText("RP Name", "rp_scbtext_small", plytext.rpnamex, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	draw.SimpleText("Name", "rp_scbtext_small", plytext.namex, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	draw.SimpleText("Job", "rp_scbtext_small", plytext.jobx, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	draw.SimpleText("Ping", "rp_scbtext_small", plytext.pingx, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	draw.SimpleText("Kills", "rp_scbtext_small", plytext.killx, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	draw.SimpleText("Deaths", "rp_scbtext_small", plytext.deathx, plytext.heady, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
	
	surface.SetDrawColor(RP.colors.scbfg.r, RP.colors.scbfg.g, RP.colors.scbfg.b, RP.colors.scbfg.a);
	//surface.DrawLine(scbx+10, plytext.heady-10, scbx+scbw-10, plytext.heady-10);
	surface.DrawLine(scbx+10, plytext.y-10, scbx+scbw-10, plytext.y-10);
	
	
	for _, ply in pairs(player.GetAll())do
		draw.SimpleText(ply:GetRPName(), "rp_scbtext_small", plytext.rpnamex, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		draw.SimpleText(ply:GetName(), "rp_scbtext_small", plytext.namex, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		draw.SimpleText(RP.jobs[ply:Team()].name, "rp_scbtext_small", plytext.jobx, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		draw.SimpleText(ply:Ping(), "rp_scbtext_small", plytext.pingx, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		draw.SimpleText(ply:Frags(), "rp_scbtext_small", plytext.killx, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		draw.SimpleText(ply:Deaths(), "rp_scbtext_small", plytext.deathx, plytext.y, RP.colors.scbtxt, TEXT_ALIGN_LEFT, TEXT_ALIGN_TOP);
		plytext.y = plytext.y + 25;
	end
	
end