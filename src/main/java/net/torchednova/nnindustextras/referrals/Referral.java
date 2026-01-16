package net.torchednova.nnindustextras.referrals;

import java.util.ArrayList;
import java.util.UUID;

public class Referral {
    Referral(int id, UUID uuid, String name, int referredBy)
    {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.referredBy = referredBy;
        this.code = String.valueOf((int)(Math.random() * 1000000));
    }

    public int id;
    public UUID uuid;
    public String name;
    public String code;
    public int referredBy;
    public ArrayList<Integer> referred;
}
