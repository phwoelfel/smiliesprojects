------------------------------------
-- by SMILIE[AUT] 
------------------------------------


function GM:HUDDrawPickupHistory( )
	RP:dbgPrint("bla");
	
end

function GM:HUDAmmoPickedUp(ItemName, Amount)
	RP:dbgPrint("ammo: "..ItemName);
	RP:dbgPrint("amount: " ..Amount);
end

function GM:HUDWeaponPickedUp(Weapon)
	RP:dbgPrint("wep: "..Weapon:GetPrintName());
end

function GM:HUDItemPickedUp(ItemName)
	RP:dbgPrint("item: "..ItemName);
end