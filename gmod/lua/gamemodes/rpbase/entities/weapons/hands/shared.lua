if (SERVER) then
	AddCSLuaFile("shared.lua")
end

if (CLIENT) then
	SWEP.PrintName = "Hands"
	SWEP.Slot = 1
	SWEP.SlotPos = 1
	SWEP.DrawAmmo = false
	SWEP.DrawCrosshair = false
end

-- Variables that are used on both client and server

SWEP.Author = "SMILE[AUT]"
SWEP.Instructions = "Left click lock, right click unlock!"
SWEP.Contact = ""
SWEP.Purpose = ""

SWEP.ViewModel			= ""
SWEP.WorldModel		= ""

SWEP.ViewModelFOV = 62
SWEP.ViewModelFlip = false
SWEP.AnimPrefix	 = "rpg"

SWEP.Spawnable = false
SWEP.AdminSpawnable = true
SWEP.Primary.ClipSize = -1
SWEP.Primary.DefaultClip = 0
SWEP.Primary.Automatic = false
SWEP.Primary.Ammo = ""
SWEP.Primary.Delay = 2 
SWEP.Primary.TakeAmmo = 0

SWEP.Secondary.ClipSize = -1
SWEP.Secondary.DefaultClip = 0
SWEP.Secondary.Automatic = false
SWEP.Secondary.Ammo = ""
SWEP.Secondary.Delay = 2 
SWEP.Secondary.TakeAmmo = 0


function SWEP:Initialize()
	//if (SERVER) then
		self:SetWeaponHoldType("normal")
	//end
end

function SWEP:Deploy()
	if (SERVER) then
		self.Owner:DrawViewModel(false)
		self.Owner:DrawWorldModel(false)
	end
end

if(SERVER)then
	function SWEP:PrimaryAttack()
		self:SetNextPrimaryFire( CurTime() + self.Primary.Delay ) 
		RunConsoleCommand("rp_lock");
	end


	function SWEP:SecondaryAttack()
		self:SetNextSecondaryFire( CurTime() + self.Secondary.Delay )
		RunConsoleCommand("rp_unlock");
	end
end