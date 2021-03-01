package com.silin.simplegame.characters;

import com.silin.simplegame.LevelsMap;

public class EnemyFactory extends Character {
    public Character getLvl(Level lvl) {
        Character toReturn = null;
        switch (lvl) {
            case LVL1:
                toReturn = new FallenAngel.CreateFallen().withStrength(LevelsMap.strengthEnemy).withHealth(LevelsMap.healthEnemy).create();
                break;
            case LVL2:
                toReturn = new FallenAngel.CreateFallen().withStrength(LevelsMap.strengthEnemy).withHealth(LevelsMap.healthEnemy).create();
                break;
            case LVL3:
                toReturn = new FallenAngel.CreateFallen().withStrength(LevelsMap.strengthEnemy).withHealth(LevelsMap.healthEnemy).create();
                break;case LVL4:
                toReturn = new Dragon.CreateDragon().create();
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
        LVL3,
        LVL4,
        LVL5,
        LVL6,
        LVL7
    }
}
