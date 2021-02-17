package com.silin.simplegame.characters;

public class Knight extends Character implements CanAttack{

    private static CreateKnight knight;

    private Knight() {
        this.health = 150;
        this.armor = 50;
        this.speed = 150;
        this.strength = 20;
        this.skillPoints = 5;
        this.level = 1;
        this.critChance = 20;
        this.critDamage = 1.3;
        this.accuracy = 10;
        this.dodge = 10;
        this.powerAttack = 0;
    }

    public static CreateKnight getKnight(){
        if (knight==null){
            knight = new CreateKnight();
        }
        return knight;
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


    public static class CreateKnight extends Knight {
        private Knight knight;

        public CreateKnight() {
            knight = new Knight();
        }

        public CreateKnight withHealth(int health) {
            knight.health = health;
            return this;
        }

        public CreateKnight withHArmor(int armor) {
            knight.armor = armor;
            return this;
        }

        public CreateKnight withSpeed(int speed) {
            knight.speed = speed;
            return this;
        }

        public Knight.CreateKnight withStrength(int strength) {
            knight.strength = strength;
            return this;
        }

        public Knight.CreateKnight withSkillPoints(int skillPoints) {
            knight.skillPoints = skillPoints;
            return this;
        }

        public Knight create() {
            return knight;
        }

        @Override
        public void attack(Character enemy) {

        }

        @Override
        public void powerAttack(Character enemy) {

        }
    }
}
