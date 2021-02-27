package com.silin.simplegame.characters;

public class Knight extends Character implements CanAttack{

   private static Knight knight;

    private Knight() {
        this.health = 100;
        this.armor = 0;
        this.speed = 50;
        this.strength = 10;
        this.skillPoints = 5;
        this.level = 1;
        this.critChance = 15;
        this.critDamage = 1.2;
        this.accuracy = 10;
        this.dodge = 10;
        this.powerAttack = 0;
        this.experience = 0;
    }

    public static Knight getKnight(){

        if (knight==null){
            knight = new Knight();
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


}
