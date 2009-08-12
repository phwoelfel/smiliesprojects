------------------------------------
-- by SMILIE[AUT] 
------------------------------------

LocalPlayer().rpmenuopen = false;

function gui_showReg(ply, cmd, args)
	local MyWindow = vgui.Create( "DFrame" );
	MyWindow:SetSize( 200, 120 );
	MyWindow:SetSizable( false );
	MyWindow:Center();
	MyWindow:SetTitle( "Welcome to the Server!" );
	MyWindow:ShowCloseButton( false );
	MyWindow:MakePopup();
	
	local Txt = vgui.Create("DLabel", MyWindow);
	Txt:SetSize(50, 20);
	Txt:SetText("RP Name:");
	Txt:SetPos(10, 30);
	
	local NameTxtf = vgui.Create("DTextEntry", MyWindow);
	NameTxtf:SetSize(100, 20);
	NameTxtf:SetPos(70, 30);
	
	local CloseButton = vgui.Create("DButton", MyWindow);
	CloseButton:SetSize(50, 20);
	CloseButton:SetPos(75, 60);
	CloseButton:SetText("Register!");
	CloseButton.DoClick = function()
			if( NameTxtf:GetValue() == "" )then
				local ErrTxt = vgui.Create("DLabel", MyWindow);
				ErrTxt:SetSize(70, 20);
				ErrTxt:SetText("Enter name!");
				ErrTxt:SetPos(65, 90);
			else
				RunConsoleCommand("rp_register", NameTxtf:GetValue());
				MyWindow:Close();
			end
		end
end
concommand.Add("rp_reggui", gui_showReg);


function gui_showJobs(ply, cmd, args)
	local MyWindow = vgui.Create("DFrame");
	MyWindow:SetSize(800, 600);
	MyWindow:SetSizable(false);
	MyWindow:Center();
	MyWindow:SetTitle("Choose your Job!");
	MyWindow:ShowCloseButton(true);
	MyWindow:MakePopup();
	MyWindow:SetDraggable(false);
	MyWindow:SetBackgroundBlur(true);
	
	local JobList = vgui.Create("DPanelList", MyWindow);
	JobList:SetPos(10, 30);
	JobList:SetSize(780, 560);
	JobList:SetSpacing(4);
	JobList:EnableHorizontal(false);
	JobList:EnableVerticalScrollbar(true);
	
	local titelpan = vgui.Create("DPanel", JobList);
	titelpan:SetSize(780,22);
	titelpan.Paint = function()
			draw.RoundedBox(10, 0, 0, titelpan:GetWide(), titelpan:GetTall(), RP.colors.jobheader);
		end
	
	local modeltitel = vgui.Create("DLabel", titelpan);
	modeltitel:SetSize(50, 20);
	modeltitel:SetText("Model");
	modeltitel:SetPos(15, 2);
	
	local jobtitel = vgui.Create("DLabel", titelpan);
	jobtitel:SetSize(100, 20);
	jobtitel:SetText("Title");
	jobtitel:SetPos(75, 2);
	
	local saltitel = vgui.Create("DLabel", titelpan);
	saltitel:SetSize(100, 20);
	saltitel:SetText("Salary");
	saltitel:SetPos(195, 2);
	
	JobList:AddItem(titelpan);
	
	for k,job in pairs(RP.jobs) do
		local pan = vgui.Create("DPanel", JobList);
		pan:SetSize(780,68);
		pan.Paint = function()
			draw.RoundedBox(6, 0, 0, pan:GetWide(), pan:GetTall(), RP.colors.jobpanel);
		end
		
		
		local icon = vgui.Create("SpawnIcon", pan);
		icon:SetModel(job.models[1]);
		icon:SetPos(2, 2);
		
		local jobtxt = vgui.Create("DLabel", pan);
		jobtxt:SetSize(100, 20);
		jobtxt:SetText(job.name);
		jobtxt:SetPos(75, 24);
		
		local saltxt = vgui.Create("DLabel", pan);
		saltxt:SetSize(100, 20);
		saltxt:SetText(job.salary);
		saltxt:SetPos(195, 24);
		
		local jobbtn = vgui.Create("DButton", pan);
		jobbtn:SetSize(50, 20);
		if(job.vote)then
			jobbtn:SetText("Apply!");
		else
			jobbtn:SetText("Change!");
		end
		jobbtn:SetPos(720, 24);
		jobbtn.DoClick = function ()
				RunConsoleCommand("rp_job", job.name);
				MyWindow:Close();
			end
		
		JobList:AddItem(pan);
	end
	
end
concommand.Add("rp_jobs", gui_showJobs);

function gui_showEntBuyMenu(ply, cmd, args)
	local MyWindow = vgui.Create("DFrame");
	MyWindow:SetSize(800, 600);
	MyWindow:SetSizable(false);
	MyWindow:Center();
	MyWindow:SetTitle("Buy stuff!");
	MyWindow:ShowCloseButton(true);
	MyWindow:MakePopup();
	MyWindow:SetDraggable(false);
	MyWindow:SetBackgroundBlur(true);
	
	local EntList = vgui.Create("DPanelList", MyWindow);
	EntList:SetPos(10, 30);
	EntList:SetSize(780, 560);
	EntList:SetSpacing(4);
	EntList:EnableHorizontal(false);
	EntList:EnableVerticalScrollbar(true);
	
	local titelpan = vgui.Create("DPanel", EntList);
	titelpan:SetSize(780,22);
	titelpan.Paint = function()
			draw.RoundedBox(10, 0, 0, titelpan:GetWide(), titelpan:GetTall(), RP.colors.entheader);
		end
	
	local modeltitel = vgui.Create("DLabel", titelpan);
	modeltitel:SetSize(50, 20);
	modeltitel:SetText("Model");
	modeltitel:SetPos(15, 2);
	
	local Enttitel = vgui.Create("DLabel", titelpan);
	Enttitel:SetSize(100, 20);
	Enttitel:SetText("Title");
	Enttitel:SetPos(75, 2);
	
	local costtitel = vgui.Create("DLabel", titelpan);
	costtitel:SetSize(100, 20);
	costtitel:SetText("Prize");
	costtitel:SetPos(195, 2);
	
	EntList:AddItem(titelpan);
	
	for k,enttbl in pairs(RP.entities) do
		if(enttbl.jobid == LocalPlayer():Team())then
			local pan = vgui.Create("DPanel", EntList);
			pan:SetSize(780,68);
			pan.Paint = function()
				draw.RoundedBox(6, 0, 0, pan:GetWide(), pan:GetTall(), RP.colors.entpanel);
			end
			
			
			local icon = vgui.Create("SpawnIcon", pan);
			icon:SetModel(enttbl.showmodel);
			icon:SetPos(2, 2);
			
			local enttxt = vgui.Create("DLabel", pan);
			enttxt:SetSize(100, 20);
			enttxt:SetText(enttbl.name);
			enttxt:SetPos(75, 24);
			
			local saltxt = vgui.Create("DLabel", pan);
			saltxt:SetSize(100, 20);
			saltxt:SetText(enttbl.prize);
			saltxt:SetPos(195, 24);
			
			local entbtn = vgui.Create("DButton", pan);
			entbtn:SetSize(50, 20);
			entbtn:SetText("Buy!");
			entbtn:SetPos(720, 24);
			entbtn.DoClick = function ()
					RunConsoleCommand("rp_buyent", enttbl.class);
					MyWindow:Close();
				end
			
			EntList:AddItem(pan);
		end
	end
	
end
concommand.Add("rp_entmenu", gui_showEntBuyMenu);

local menu;
function gui_showPlyMenu(ply, cmd, args)
	if(ply.rpmenuopen)then gui.EnableScreenClicker(false) ply.rpmenuopen = false /*if(menu)then  menu:Hide() end*/ return end
	ply.rpmenuopen = true;
	menu = DermaMenu();
	menu:SetDeleteSelf(true);
	menu:SetPos(ScrW()/2, ScrH()/2);
	menu:AddOption("buy", function() RunConsoleCommand("rp_buy") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("sell", function() RunConsoleCommand("rp_sell") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("lock", function() RunConsoleCommand("rp_lock") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("unlock", function() RunConsoleCommand("rp_unlock") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	local sub = menu:AddSubMenu("give");
	sub:AddOption("1", function() RunConsoleCommand("rp_give", 1) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("5", function() RunConsoleCommand("rp_give", 5) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("10", function() RunConsoleCommand("rp_give", 10) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("20", function() RunConsoleCommand("rp_give", 20) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("25", function() RunConsoleCommand("rp_give", 5) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("50", function() RunConsoleCommand("rp_give", 50) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("100", function() RunConsoleCommand("rp_give", 100) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("200", function() RunConsoleCommand("rp_give", 200) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("custom", function() gui.EnableScreenClicker(false);
									ply.rpmenuopen = false;
									local MyWindow = vgui.Create( "DFrame" );
									MyWindow:SetSize( 200, 120 );
									MyWindow:SetSizable( false );
									MyWindow:Center();
									MyWindow:SetTitle( "custom amount" );
									MyWindow:ShowCloseButton( false );
									MyWindow:MakePopup();
									
									local Txt = vgui.Create("DLabel", MyWindow);
									Txt:SetSize(50, 20);
									Txt:SetText("Amount:");
									Txt:SetPos(10, 30);
									
									local AmountTxtf = vgui.Create("DTextEntry", MyWindow);
									AmountTxtf:SetSize(100, 20);
									AmountTxtf:SetPos(70, 30);
									
									local CloseButton = vgui.Create("DButton", MyWindow);
									CloseButton:SetSize(50, 20);
									CloseButton:SetPos(75, 60);
									CloseButton:SetText("Give!");
									CloseButton.DoClick = function()
											if( AmountTxtf:GetValue() == "" )then
												local ErrTxt = vgui.Create("DLabel", MyWindow);
												ErrTxt:SetSize(70, 20);
												ErrTxt:SetText("Enter amount!");
												ErrTxt:SetPos(65, 90);
											else
												RunConsoleCommand("rp_give", AmountTxtf:GetValue());
												MyWindow:Close();
											end
										end
								end );
	gui.EnableScreenClicker(true);
end
concommand.Add("rp_plymenu", gui_showPlyMenu);

function um_JobVote(um)
	local ply = um:ReadEntity();
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
	namelab:SetText(ply:GetRPName());
	//namelab:SetPos(15, 25);
	namelab:Center(); 
	local x, y = namelab:GetPos();
	namelab:SetPos(x, 25);
	
	local textlab = vgui.Create("DLabel", MyWindow);
	textlab:SetSize(170, 50);
	textlab:SetText(" wants to become a " ..jobname);
	//textlab:SetPos(15, 25);
	textlab:Center();
	
	local yesbtn = vgui.Create("DButton", MyWindow);
	yesbtn:SetSize(30, 20);
	yesbtn:SetText("Yes!");
	yesbtn:SetPos(15, 60);
	yesbtn.DoClick = function ()
			RunConsoleCommand("rp_vote", ply:UserID(), 1);
			MyWindow:Close();
		end
	
	local nobtn = vgui.Create("DButton", MyWindow);
	nobtn:SetSize(30, 20);
	nobtn:SetText("No!");
	nobtn:SetPos(155, 60);
	nobtn.DoClick = function ()
			RunConsoleCommand("rp_vote", ply:UserID(), 0);
			MyWindow:Close();
		end
	
end
usermessage.Hook("rp_jobvoting", um_JobVote);



