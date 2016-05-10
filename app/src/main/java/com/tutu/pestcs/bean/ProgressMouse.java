package com.tutu.pestcs.bean;

/**
 * Created by tutu on 16/4/9.
 */
public class ProgressMouse {
    private String UnitType;
    private int OriginalNumS;
    private int OriginalNumE;
    private int CheakedNumS;
    private int CheakedNumE;
    private int ToCheakNumS;
    private int ToCheakNumE;
    private boolean Indoor;

    public ProgressMouse(String unitType, int originalNumS, int originalNumE, int cheakedNumS, int cheakedNumE, int
            toCheakNumS, int toCheakNumE, boolean indoor) {
        UnitType = unitType;
        OriginalNumS = originalNumS;
        OriginalNumE = originalNumE;
        CheakedNumS = cheakedNumS;
        CheakedNumE = cheakedNumE;
        ToCheakNumS = toCheakNumS;
        ToCheakNumE = toCheakNumE;
        Indoor = indoor;
    }


    public String getUnitType() {
        return UnitType;
    }

    public void setUnitType(String unitType) {
        UnitType = unitType;
    }

    public int getOriginalNumS() {
        return OriginalNumS;
    }

    public void setOriginalNumS(int originalNumS) {
        OriginalNumS = originalNumS;
    }

    public int getOriginalNumE() {
        return OriginalNumE;
    }

    public void setOriginalNumE(int originalNumE) {
        OriginalNumE = originalNumE;
    }

    public int getCheakedNumS() {
        return CheakedNumS;
    }

    public void setCheakedNumS(int cheakedNumS) {
        CheakedNumS = cheakedNumS;
    }

    public int getCheakedNumE() {
        return CheakedNumE;
    }

    public void setCheakedNumE(int cheakedNumE) {
        CheakedNumE = cheakedNumE;
    }

    public int getToCheakNumS() {
        return ToCheakNumS;
    }

    public void setToCheakNumS(int toCheakNumS) {
        ToCheakNumS = toCheakNumS;
    }

    public int getToCheakNumE() {
        return ToCheakNumE;
    }

    public void setToCheakNumE(int toCheakNumE) {
        ToCheakNumE = toCheakNumE;
    }

    public boolean isIndoor() {
        return Indoor;
    }

    public void setIndoor(boolean indoor) {
        Indoor = indoor;
    }

    @Override
    public String toString() {
        return "ProgressMouse{" +
                "UnitType='" + UnitType + '\'' +
                ", OriginalNumS=" + OriginalNumS +
                ", OriginalNumE=" + OriginalNumE +
                ", CheakedNumS=" + CheakedNumS +
                ", CheakedNumE=" + CheakedNumE +
                ", ToCheakNumS=" + ToCheakNumS +
                ", ToCheakNumE=" + ToCheakNumE +
                ", Indoor=" + Indoor +
                '}';
    }
}
