------------------------------------
-- by SMILIE[AUT] 
------------------------------------

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
	
	local JobList = vgui.Create("DPanelList", MyWindow);
	JobList:SetPos(10, 30);
	JobList:SetSize(780, 560);
	JobList:SetSpacing(4);
	JobList:EnableHorizontal(false);
	JobList:EnableVerticalScrollbar(true);
	
	local titelpan = vgui.Create("DPanel", JobList);
	titelpan:SetSize(780,22);
	
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
		local icon = vgui.Create("SpawnIcon", pan);
		icon:SetModel(job.models[1]);
		icon:SetPos(2, 2);
		icon.DoClick = function ()
				RunConsoleCommand("rp_job", job.name);
				MyWindow:Close();
			end
		
		local jobtxt = vgui.Create("DLabel", pan);
		jobtxt:SetSize(100, 20);
		jobtxt:SetText(job.name);
		jobtxt:SetPos(75, 24);
		
		local saltxt = vgui.Create("DLabel", pan);
		saltxt:SetSize(100, 20);
		saltxt:SetText(job.salary);
		saltxt:SetPos(195, 24);
		
		
		JobList:AddItem(pan);
	end
	
end
concommand.Add("rp_jobs", gui_showJobs);