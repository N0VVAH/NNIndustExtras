package net.torchednova.nnindustextras.referrals;

import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ReferralManager {
    public static ArrayList<Referral> rs;

    public static void init()
    {
        rs = new ArrayList<>();

    }

    public static String getCode(Entity player)
    {
        UUID uuid = player.getUUID();

        for (int i = 0; i < rs.size(); i++) {
            if (Objects.equals(rs.get(i).uuid.toString(), uuid.toString()))
            {
                return rs.get(i).code;
            }
        }

        return newReferrer(player);

    }

    public static String newReferrer(Entity player)
    {
        rs.add(new Referral(rs.size(), player.getUUID(), player.getScoreboardName(), -1));

        return rs.getLast().code;
    }

    public static boolean addReferry(Entity player, String code)
    {

        Referral rf = getReferral(code);
        if(rf == null)
        {
            return false;
        }
        Referral ry = getReferral(player.getUUID());
        if (ry != null)
        {
            return false;
        }
        ry = newReffered(player, rf.id);
        if (ry == null)
        {
            return false;
        }

        rf.referred.add(ry.id);

        return true;


    }

    public static Referral newReffered(Entity player, int referredBy)
    {
        rs.add(new Referral(rs.size(), player.getUUID(), player.getScoreboardName(), referredBy));


        return null;
    }
    public static Referral getReferral(String code)
    {
        for (int i = 0; i < rs.size(); i++)
        {
            if (Objects.equals(rs.get(i).code, code))
            {
                return rs.get(i);
            }
        }

        return null;
    }

    public static Referral getReferral(UUID uuid)
    {
        for (int i = 0; i < rs.size(); i++)
        {
            if (Objects.equals(rs.get(i).uuid.toString(), uuid.toString()))
            {
                return rs.get(i);
            }
        }

        return null;
    }





}
