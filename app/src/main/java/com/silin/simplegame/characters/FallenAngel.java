package com.silin.simplegame.characters;

public class FallenAngel extends Character {

    public FallenAngel() {
        this.health = 100;
        this.armor = 50;
        this.speed = 150;
        this.strength = 10;
        this.skillPoints = 5;
        this.level = 1;
        this.critChance = 10;
        this.critDamage = 1.1;
        this.accuracy = 20;
        this.dodge = 10;
        this.powerAttack = 0;
    }

    @Override
    public void attack(Character enemy) {
       int probHitting = accuracy + (int) (Math.random() * 20);
       int probDodge = enemy.getDodge() + (int) (Math.random() * 20);
       if (probHitting >= probDodge){
       int modifier = 101 - critChance;
       int random = critChance + (int) (Math.random() * modifier);
        if (random > 90){
            enemy.setHealth(enemy.getHealth() - (int)Math.round(strength * critDamage) );
        }else enemy.setHealth(enemy.getHealth() - strength);powerAttack++;}

    }

    @Override
    public void powerAttack(Character enemy) {
        enemy.setHealth(enemy.getHealth() - strength * 3);
    }


    public static class CreateFallen extends Character {
        private  FallenAngel fallenAngel;

        public CreateFallen() {
            fallenAngel = new FallenAngel();
        }

        public CreateFallen withHealth(int health){
            fallenAngel.health = health;
            return this;
        }

        public CreateFallen withHArmor(int armor){
            fallenAngel.armor = armor;
            return this;
        }

        public CreateFallen withSpeed(int speed){
            fallenAngel.speed = speed;
            return this;
        }

        public CreateFallen withStrength(int strength){
            fallenAngel.strength = strength;
            return this;
        }

        public CreateFallen withSkillPoints(int skillPoints){
            fallenAngel.skillPoints = skillPoints;
            return this;
        }

        public FallenAngel create(){
            return fallenAngel;
        }

        @Override
        public void attack(Character enemy) {

        }

        @Override
        public void powerAttack(Character enemy) {

        }
    }
}
