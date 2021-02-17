package com.silin.simplegame.characters;

import com.silin.simplegame.LevelsMap;

public class EnemyFactory extends Character {
    public Character getLvl(Level lvl) {
        Character toReturn = null;
        switch (lvl) {
            case LVL1:
                toReturn = new FallenAngel.CreateFallen().withStrength(LevelsMap.strengthLvl1).withHealth(LevelsMap.health).create();
                break;
            case LVL2:
                toReturn = new FallenAngel();
                break;
            case LVL3:
                toReturn = new FallenAngel();
                break;
        }
        return toReturn;
    }

    @Override
    public void attack(Character enemy) {

    }

    @Override
    public void powerAttack(Character enemy) {

    }

    public enum Level {
        LVL1,
        LVL2,
        LVL3
    }
}
