------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

function showRegGui()
	local MyWindow = vgui.Create( "DFrame" );
	MyWindow:SetSize( 150, 80 );
	MyWindow:SetSizable( false );
	MyWindow:Center();
	MyWindow:SetTitle( "Welcome to the Server!" );
	MyWindow:ShowCloseButton( false );
	MyWindow:MakePopup();
	
	local Txt = vgui.Create("DLabel", MyWindow);
	Txt:SetSize(30, 20);
	Txt:SetText("RP Name:");
	Txt:SetPos(20, 20);
	
	local NameTxtf = vgui.Create("DTextEntry", MyWindow);
	NameTxtf:SetSize(70, 20);
	NameTxtf:SetPos(60, 20);
	
	local CloseButton = vgui.Create("DButton", MyWindow);
	CloseButton:SetSize(30, 20);
	CloseButton:SetPos(60, 50);
	CloseButton:SetText("Save!");
	CloseButton.DoClick = function()
			if( NameTxtf:GetValue() == "" )then
				local ErrTxt = vgui.Create("DLabel", MyWindow);
				ErrTxt:SetSize(50, 20);
				ErrTxt:SetText("Enter Name!");
				ErrTxt:SetPos(80, 50);
			else
				RunConsoleCommand("rp_register", NameTxtf:GetValue());
				MyWindow:Close();
			end
		end
end
//concommand.Add("rp_reggui", showRegGui);

function umgRegisterUser()
	showRegGui();
end
usermessage.Hook("rp_registeruser", umgRegisterUser);