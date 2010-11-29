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
	
	local tabs = vgui.Create("DPropertySheet", MyWindow)
	tabs:SetPos(5, 30)
	tabs:SetSize(790, 565)
	
	
	---------------------------------------- Joblist ------------------------------------------
	local JobList = vgui.Create("DPanelList");
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
			draw.RoundedBox(6, 0, 0, pan:GetWide(), pan:GetTall(), job.color);//RP.colors.jobpanel
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
		if(#RP.jobs>7)then
			jobbtn:SetPos(pan:GetWide()-80, 24);
		else
			jobbtn:SetPos(pan:GetWide()-60, 24);
		end
		jobbtn.DoClick = function ()
				RunConsoleCommand("rp_job", job.name);
				MyWindow:Close();
			end
		
		JobList:AddItem(pan);
	end
	
	
	---------------------------------------- modellist ------------------------------------------
	local ModelList = vgui.Create("DPanelList");
	ModelList:SetPos(10, 30);
	ModelList:SetSize(780, 560);
	ModelList:SetSpacing(4);
	ModelList:EnableHorizontal(true);
	ModelList:EnableVerticalScrollbar(true);
	
	for k, model in pairs(RP.jobs[LocalPlayer():Team()].models) do
		local icon = vgui.Create("SpawnIcon");
		icon:SetModel(model);
		icon.DoClick = function() RunConsoleCommand("rp_model", model); MyWindow:Close(); end
		ModelList:AddItem(icon);
	end
	
	
	tabs:AddSheet( "Jobs", JobList, "gui/silkicons/user", false, false, "Here you can change your job." )
	tabs:AddSheet( "Model", ModelList, "gui/silkicons/group", false, false, "Here you can change your model." )
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
	
	local tabs = vgui.Create("DPropertySheet", MyWindow)
	tabs:SetPos(5, 30)
	tabs:SetSize(790, 565)
	
	---------------------------------------- Entitybuymenu ------------------------------------------
	local EntList = vgui.Create("DPanelList");
	EntList:SetPos(5, 5);
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
	
	for k,enttbl in pairs(RP:GetEntitiesByJob(ply:Team())) do
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
		
		local prizetxt = vgui.Create("DLabel", pan);
		prizetxt:SetSize(100, 20);
		prizetxt:SetText(enttbl.prize);
		prizetxt:SetPos(195, 24);
		
		local entbtn = vgui.Create("DButton", pan);
		entbtn:SetSize(50, 20);
		entbtn:SetText("Buy!");
		if(#RP:GetEntitiesByJob(ply:Team())>7)then
			entbtn:SetPos(pan:GetWide()-80, 24);
		else
			entbtn:SetPos(pan:GetWide()-60, 24);
		end
		entbtn.DoClick = function ()
				RunConsoleCommand("rp_buyent", enttbl.class);
				MyWindow:Close();
			end
		
		EntList:AddItem(pan);
	end
	
	---------------------------------------- Wepaonbuymenu ------------------------------------------
	local WepList = vgui.Create("DPanelList");
	WepList:SetPos(5, 5);
	WepList:SetSize(780, 560);
	WepList:SetSpacing(4);
	WepList:EnableHorizontal(false);
	WepList:EnableVerticalScrollbar(true);
	
	local weptitelpan = vgui.Create("DPanel", WepList);
	weptitelpan:SetSize(780,22);
	weptitelpan.Paint = function()
			draw.RoundedBox(10, 0, 0, weptitelpan:GetWide(), weptitelpan:GetTall(), RP.colors.wepheader);
		end
	
	local wepmodeltitel = vgui.Create("DLabel", weptitelpan);
	wepmodeltitel:SetSize(50, 20);
	wepmodeltitel:SetText("Model");
	wepmodeltitel:SetPos(15, 2);
	
	local Weptitel = vgui.Create("DLabel", weptitelpan);
	Weptitel:SetSize(100, 20);
	Weptitel:SetText("Title");
	Weptitel:SetPos(75, 2);
	
	local wepcosttitel = vgui.Create("DLabel", weptitelpan);
	wepcosttitel:SetSize(100, 20);
	wepcosttitel:SetText("Prize");
	wepcosttitel:SetPos(195, 2);
	
	local wepammotitel = vgui.Create("DLabel", weptitelpan);
	wepammotitel:SetSize(100, 20);
	wepammotitel:SetText("Included Ammo");
	wepammotitel:SetPos(315, 2);
	
	WepList:AddItem(weptitelpan);
	
	for k,weptbl in pairs(RP:GetWeaponsByJob(ply:Team())) do
		local pan = vgui.Create("DPanel", WepList);
		pan:SetSize(780,68);
		pan.Paint = function()
			draw.RoundedBox(6, 0, 0, pan:GetWide(), pan:GetTall(), RP.colors.weppanel);
		end
		
		
		local icon = vgui.Create("SpawnIcon", pan);
		icon:SetModel(weptbl.showmodel);
		icon:SetPos(2, 2);
		
		local weptxt = vgui.Create("DLabel", pan);
		weptxt:SetSize(100, 20);
		weptxt:SetText(weptbl.name);
		weptxt:SetPos(75, 24);
		
		local prizetxt = vgui.Create("DLabel", pan);
		prizetxt:SetSize(100, 20);
		prizetxt:SetText(weptbl.prize);
		prizetxt:SetPos(195, 24);
		
		local wepammo = vgui.Create("DLabel", pan);
		wepammo:SetSize(100, 20);
		wepammo:SetText(weptbl.ammo[2]);
		wepammo:SetPos(315, 24);
		
		local wepbtn = vgui.Create("DButton", pan);
		wepbtn:SetSize(50, 20);
		wepbtn:SetText("Buy!");
		if(#RP:GetWeaponsByJob(ply:Team())>7)then
			wepbtn:SetPos(pan:GetWide()-80, 24);
		else
			wepbtn:SetPos(pan:GetWide()-60, 24);
		end
		wepbtn.DoClick = function ()
				RunConsoleCommand("rp_buyswep", weptbl.class);
				MyWindow:Close();
			end
		
		WepList:AddItem(pan);
	end
	
	---------------------------------------- Ammobuymenu ------------------------------------------
	local AmmoList = vgui.Create("DPanelList");
	AmmoList:SetPos(5, 5);
	AmmoList:SetSize(780, 560);
	AmmoList:SetSpacing(4);
	AmmoList:EnableHorizontal(false);
	AmmoList:EnableVerticalScrollbar(true);
	
	local ammotitelpan = vgui.Create("DPanel", AmmoList);
	ammotitelpan:SetSize(780,22);
	ammotitelpan.Paint = function()
			draw.RoundedBox(10, 0, 0, ammotitelpan:GetWide(), ammotitelpan:GetTall(), RP.colors.ammoheader);
		end
	
	local ammomodeltitel = vgui.Create("DLabel", ammotitelpan);
	ammomodeltitel:SetSize(50, 20);
	ammomodeltitel:SetText("Model");
	ammomodeltitel:SetPos(15, 2);
	
	local Ammotitel = vgui.Create("DLabel", ammotitelpan);
	Ammotitel:SetSize(100, 20);
	Ammotitel:SetText("Title");
	Ammotitel:SetPos(75, 2);
	
	local ammocosttitel = vgui.Create("DLabel", ammotitelpan);
	ammocosttitel:SetSize(100, 20);
	ammocosttitel:SetText("Prize");
	ammocosttitel:SetPos(195, 2);
	
	local ammoammounttitel = vgui.Create("DLabel", ammotitelpan);
	ammoammounttitel:SetSize(100, 20);
	ammoammounttitel:SetText("Amount");
	ammoammounttitel:SetPos(315, 2);
	
	AmmoList:AddItem(ammotitelpan);
	
	for k,ammotbl in pairs(RP:GetAmmoByJob(ply:Team())) do
		local pan = vgui.Create("DPanel", AmmoList);
		pan:SetSize(780,68);
		pan.Paint = function()
			draw.RoundedBox(6, 0, 0, pan:GetWide(), pan:GetTall(), RP.colors.ammopanel);
		end
		
		
		local icon = vgui.Create("SpawnIcon", pan);
		icon:SetModel(ammotbl.showmodel);
		icon:SetPos(2, 2);
		
		local ammotxt = vgui.Create("DLabel", pan);
		ammotxt:SetSize(100, 20);
		ammotxt:SetText(ammotbl.name);
		ammotxt:SetPos(75, 24);
		
		local saltxt = vgui.Create("DLabel", pan);
		saltxt:SetSize(100, 20);
		saltxt:SetText(ammotbl.prize);
		saltxt:SetPos(195, 24);
		
		local wepammo = vgui.Create("DLabel", pan);
		wepammo:SetSize(100, 20);
		wepammo:SetText(ammotbl.amount);
		wepammo:SetPos(315, 24);
		
		local ammobtn = vgui.Create("DButton", pan);
		ammobtn:SetSize(50, 20);
		ammobtn:SetText("Buy!");
		if(#RP:GetAmmoByJob(ply:Team())>7)then
			ammobtn:SetPos(pan:GetWide()-80, 24);
		else
			ammobtn:SetPos(pan:GetWide()-60, 24);
		end
		ammobtn.DoClick = function ()
				RunConsoleCommand("rp_buyammo", ammotbl.type);
			end
		
		AmmoList:AddItem(pan);
	end
	
	tabs:AddSheet( "Entities", EntList, "gui/silkicons/wrench", false, false, "Here you can buy entities." )
	tabs:AddSheet( "Weapons", WepList, "gui/silkicons/bomb", false, false, "Here you can buy weapons." )
	tabs:AddSheet( "Ammunition", AmmoList, "gui/silkicons/add", false, false, "Here you can buy ammo." )
end
concommand.Add("rp_entmenu", gui_showEntBuyMenu);



local menu;
function gui_showPlyMenu(ply, cmd, args)
	if(ply.rpmenuopen)then gui.EnableScreenClicker(false) ply.rpmenuopen = false  return end //if(menu)then  menu:Hide() end
	ply.rpmenuopen = true;
	menu = DermaMenu();
	menu:SetDeleteSelf(true);
	menu:SetPos(ScrW()/2, ScrH()/2);
	menu:AddOption("Buy", function() RunConsoleCommand("rp_buy") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("Sell", function() RunConsoleCommand("rp_sell") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("Lock", function() RunConsoleCommand("rp_lock") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	menu:AddOption("Unlock", function() RunConsoleCommand("rp_unlock") gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	local sub = menu:AddSubMenu("Give");
	sub:AddOption("1", function() RunConsoleCommand("rp_give", 1) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("5", function() RunConsoleCommand("rp_give", 5) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("10", function() RunConsoleCommand("rp_give", 10) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("20", function() RunConsoleCommand("rp_give", 20) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("25", function() RunConsoleCommand("rp_give", 5) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("50", function() RunConsoleCommand("rp_give", 50) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("100", function() RunConsoleCommand("rp_give", 100) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("200", function() RunConsoleCommand("rp_give", 200) gui.EnableScreenClicker(false) ply.rpmenuopen = false; end );
	sub:AddOption("Custom", function() gui.EnableScreenClicker(false);
									ply.rpmenuopen = false;
									local MyWindow = vgui.Create( "DFrame" );
									MyWindow:SetSize( 200, 120 );
									MyWindow:SetSizable( false );
									MyWindow:Center();
									MyWindow:SetTitle( "Custom amount." );
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

/*
function gui_showPlyMenu(ply, cmd, args)
	local MyWindow = vgui.Create("DFrame");
	MyWindow:SetSizable(false);
	MyWindow:Center();
	MyWindow:SetTitle("Do something!");
	MyWindow:ShowCloseButton(true);
	MyWindow:SetDraggable(true);
	MyWindow:SetBackgroundBlur(false);
	
	local lst = vgui.Create("DPanelList", MyWindow);
	lst:SetAutoSize(true);
	lst:SetSpacing(0);
	lst:EnableHorizontal(false);
	lst:EnableVerticalScrollbar(true);
	lst:SetPos(10, 30);
	lst:SetSize(180, 500);

	local anzcat = 0;
	local anzbtn = 0;
	for k,men in pairs(RP.plymenu) do
		anzcat = anzcat+1;
		RP:print("men: " ..k);
		RP:dbgPrintTable(men);
		RP:print("menend");
		local cat = vgui.Create("DCollapsibleCategory", lst);
		cat:SetExpanded(1);
		cat:SetLabel(men.titel);
		
		local conts = vgui.Create("DPanelList");
		conts:SetAutoSize(true);
		conts:SetSpacing(0);
		conts:EnableHorizontal(false);
		conts:EnableVerticalScrollbar(false);
		for i=1,table.getn(men) do
			RP:dbgPrintTable(men[i]);
			local btn = vgui.Create("DButton");
			btn:SetSize(150, 30);
			btn:SetText(men[i].titel);
			btn.DoClick = men[i].onclick;
			conts:AddItem(btn);
			anzbtn = anzbtn+1;
		end
		
		cat:SetContents(conts);
		lst:AddItem(cat);
	end
	RP:print("anzcat: " ..tostring(anzcat));
	RP:print("anzbtn: " ..tostring(anzbtn));
	RP:print("size: " ..tostring(50+anzcat*25+anzbtn*30));
	MyWindow:SetSize(200, 50+anzcat*25+anzbtn*30);
	MyWindow:MakePopup();
end*/
concommand.Add("rp_plymenu", gui_showPlyMenu);



