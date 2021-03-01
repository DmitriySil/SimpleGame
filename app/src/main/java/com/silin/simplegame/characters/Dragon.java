package com.silin.simplegame.characters;

public class Dragon extends Character implements CanAttack{

    public Dragon() {
        this.health = 500;
        this.armor = 200;
        this.speed = 200;
        this.strength = 35;
        this.skillPoints = 0;
        this.level = 3;
        this.critChance = 30;
        this.critDamage = 1.3;
        this.accuracy = 20;
        this.dodge = 1;
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

    }

    public static class CreateDragon extends Character {
        private  Dragon dragon;

        public CreateDragon() {
            dragon = new Dragon();
        }

        public Dragon.CreateDragon withHealth(int health){
            dragon.health = health;
            return this;
        }

        public Dragon.CreateDragon withHArmor(int armor){
            dragon.armor = armor;
            return this;
        }

        public Dragon.CreateDragon withSpeed(int speed){
            dragon.speed = speed;
            return this;
        }

        public Dragon.CreateDragon withStrength(int strength){
            dragon.strength = strength;
            return this;
        }

        public Dragon.CreateDragon withSkillPoints(int skillPoints){
            dragon.skillPoints = skillPoints;
            return this;
        }

        public Dragon create(){
            return dragon;
        }

        @Override
        public void attack(Character enemy) {

        }

        @Override
        public void powerAttack(Character enemy) {

        }
    }
}
