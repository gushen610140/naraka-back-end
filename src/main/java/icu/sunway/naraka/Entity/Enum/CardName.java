package icu.sunway.naraka.Entity.Enum;

public enum CardName {
    zhude,
    garbage,
    cy,
    zw,
    lyb,
    ssj,
    bb,
    qj,
    lyh,
    cjh,
    jyy,
    why;


    public static CardName randomCardName() {
        return CardName.values()[(int) (Math.random() * CardName.values().length)];
    }
}
