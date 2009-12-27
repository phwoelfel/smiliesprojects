RP.plymenu = {};

RP.plymenu[1] = {};
RP.plymenu[1].titel = "Buymenu";
RP.plymenu[1][1] = {};
RP.plymenu[1][1].titel = "buy";
RP.plymenu[1][1].onclick = function() RunConsoleCommand("rp_buy") end
RP.plymenu[1][2] = {};
RP.plymenu[1][2].titel = "sell";
RP.plymenu[1][2].onclick = function() RunConsoleCommand("rp_sell") end
RP.plymenu[1][3] = {};
RP.plymenu[1][3].titel = "lock";
RP.plymenu[1][3].onclick = function() RunConsoleCommand("rp_lock") end
RP.plymenu[1][4] = {};
RP.plymenu[1][4].titel = "unlock";
RP.plymenu[1][4].onclick = function() RunConsoleCommand("rp_unlock") end
RP.plymenu[1][5] = {};
RP.plymenu[1][5].titel = "set title";
RP.plymenu[1][5].onclick = function() 
								local MyWindow = vgui.Create( "DFrame" );
								MyWindow:SetSize( 200, 120 );
								MyWindow:SetSizable( false );
								MyWindow:Center();
								MyWindow:SetTitle( "Set title." );
								MyWindow:ShowCloseButton( false );
								MyWindow:MakePopup();
								
								local Txt = vgui.Create("DLabel", MyWindow);
								Txt:SetSize(50, 20);
								Txt:SetText("Title:");
								Txt:SetPos(10, 30);
								
								local TitleTxtf = vgui.Create("DTextEntry", MyWindow);
								TitleTxtf:SetSize(100, 20);
								TitleTxtf:SetPos(70, 30);
								
								local CloseButton = vgui.Create("DButton", MyWindow);
								CloseButton:SetSize(50, 20);
								CloseButton:SetPos(75, 60);
								CloseButton:SetText("Set title!");
								CloseButton.DoClick = function()
										RunConsoleCommand("rp_settitle", TitleTxtf:GetValue());
										MyWindow:Close();
									end 
							end

RP.plymenu[2] = {};
RP.plymenu[2].titel = "Moneymenu";
RP.plymenu[2][1] = {};
RP.plymenu[2][1].titel = "give";
RP.plymenu[2][1].onclick =
	function() 
		local menu = DermaMenu();
		menu:AddOption("1", function() RunConsoleCommand("rp_give", 1) end );
		menu:AddOption("5", function() RunConsoleCommand("rp_give", 5) end );
		menu:AddOption("10", function() RunConsoleCommand("rp_give", 10) end );
		menu:AddOption("20", function() RunConsoleCommand("rp_give", 20) end );
		menu:AddOption("25", function() RunConsoleCommand("rp_give", 5) end );
		menu:AddOption("50", function() RunConsoleCommand("rp_give", 50) end );
		menu:AddOption("100", function() RunConsoleCommand("rp_give", 100) end );
		menu:AddOption("200", function() RunConsoleCommand("rp_give", 200) end );
		menu:AddOption("Custom", function() 
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
		menu:Open();
		
		end

/*
RP.plymenu[1][] = {};
RP.plymenu[1][].titel = "";
RP.plymenu[1][].onclick = function() RunConsoleCommand("rp_") end
*/