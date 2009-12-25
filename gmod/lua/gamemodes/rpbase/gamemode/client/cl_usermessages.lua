------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function um_SpawnSent(um)
	local entname = um:ReadString();
	RunConsoleCommand("gm_spawnsent", entname);
end
usermessage.Hook("RP_spawnsent", um_SpawnSent);

function um_GiveSwep(um)
	local entname = um:ReadString();
	RunConsoleCommand("gm_giveswep", entname);
end
usermessage.Hook("RP_giveswep", um_GiveSwep);

function um_JobVote(um)
	local plyid = um:ReadString();
	local plyname = um:ReadString();
	local jobname = um:ReadString();
	
	local MyWindow = vgui.Create("DFrame");
	MyWindow:SetSize(200, 100);
	MyWindow:SetSizable(false);
	MyWindow:SetPos(10, ScrH()/2-100);
	MyWindow:SetTitle("Vote for change!");
	MyWindow:ShowCloseButton(false);
	MyWindow:MakePopup();
	MyWindow:SetDraggable(false);
	MyWindow:SetBackgroundBlur(false);
	
	local namelab = vgui.Create("DLabel", MyWindow);
	namelab:SetSize(170, 20);
	namelab:SetText(plyname);
	//namelab:SetPos(15, 25);
	namelab:Center(); 
	x, y = namelab:GetPos();
	RP:dbgPrint(x .." " ..y);
	namelab:SetPos(x, 25);
	
	local textlab = vgui.Create("DLabel", MyWindow);
	textlab:SetSize(170, 50);
	textlab:SetText("wants to become a " ..jobname);
	//textlab:SetPos(15, 25);
	textlab:Center();
	
	local yesbtn = vgui.Create("DButton", MyWindow);
	yesbtn:SetSize(30, 20);
	yesbtn:SetText("Yes!");
	yesbtn:SetPos(15, 60);
	yesbtn.DoClick = function ()
			RunConsoleCommand("rp_vote", plyid, 1);
			MyWindow:Close();
		end
	
	local nobtn = vgui.Create("DButton", MyWindow);
	nobtn:SetSize(30, 20);
	nobtn:SetText("No!");
	nobtn:SetPos(155, 60);
	nobtn.DoClick = function ()
			RunConsoleCommand("rp_vote", plyid, 0);
			MyWindow:Close();
		end
	
end
usermessage.Hook("rp_jobvoting", um_JobVote);
