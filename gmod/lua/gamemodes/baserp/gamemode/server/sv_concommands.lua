------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

function ccRegisterUser(ply, cmd, args)
	if(!args) then return end
	local sqlusrdata = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..ply:UniqueID());
	if(sqlusrdata)then
		ply:ChatPrint("You have already created a useraccount!");
	else
		sql.Query("insert into " ..GetConVar("rp_sqltable"):GetString() .."(uid, rpname, money) values(" ..ply:UniqueID() ..", " ..SQLStr(args[1]) ..", " ..GetConVar("rp_startmoney"):GetInt() ..")")
		if(sql.LastError())then
			print(sql.LastError());
		else
			ply:ChatPrint("Your useraccount has been created!");
		end
		/*else
			ply:ChatPrint("Error while creating useraccount in database, your stuff won't be saved! Try rejoin!");
		end*/
	end
end
concommand.Add("rp_register", ccRegisterUser);

function ccShowUsersFromDB(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local sqlusers = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString());
	if(sqlusers)then
		PrintTable(sqlusers);
	else
		print("Error: " ..sql.LastError());
	end

end
concommand.Add("rp_showusers", ccShowUsersFromDB);