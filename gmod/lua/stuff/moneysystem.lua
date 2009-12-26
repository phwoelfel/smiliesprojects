function plyInit(ply)
	local dataFName = "ms/data.txt";
	if not file.Exists(dataFName) then
		file.Write(dataFName, ply:SteamID() .. ":1000");
	end
	
	fData = file.Read(dataFName);
	plyStrData = string.Explode("\n", fData);
	PrintTable(plyStrData);
	
	
end
hook.Add("PlayerInitialSpawn", "MS_initSpawn", plyInit);