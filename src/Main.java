import java.util.Scanner;
import java.util.Random;

class Hero
{
    String name;
    int health;
    int defense;
    Weapon equippedWeapon;

    void create()
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("Valiant warrior, give yourself a name: ");
        name = sc.nextLine();
        Random h1 = new Random();
        health = h1.nextInt(51)+50;
    }

    void equip(Weapon w)
    {
        this.equippedWeapon = w;
        System.out.println(name + " equipped with "+w.name+".");
    }
}
class Villain
{
    String name;
    int health;
    int defense;
    Weapon equippedWeapon;

    void create()
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("Valiant warrior, give yourself a name: ");
        name = sc.nextLine();
        Random h1 = new Random();
        health = h1.nextInt(51)+50;
    }

    void equip(Weapon w)
    {
        this.equippedWeapon = w;
        System.out.println(name + " equipped with "+w.name+".");
    }
}
class Weapon
{
    String type;
    String name;
    int attack;

    void select()
    {
        System.out.println("WEAPON TYPES: Sword (S) | Gun (G) | Bow and Arrow (A)");
        System.out.print("Enter the character of the weapon you want to equip yourself with: ");
        Scanner sc = new Scanner (System.in);
        String choice = sc.nextLine();

        if(choice.equalsIgnoreCase("S"))
        {
            type = "Sword";
            System.out.print("Name your "+type+":");
            name = sc.nextLine();
            attack = 20;
        }
        else if(choice.equalsIgnoreCase("G"))
        {
            type = "Gun";
            System.out.print("Name your "+type+":");
            name = sc.nextLine();
            attack = 30;
        }
        else
        {
            type = "Bow and Arrow";
            System.out.print("Name your "+type+":");
            name = sc.nextLine();
            attack = 10;
        }


    }
}

public class Main
{
    public static void main(String args[])
    {
        Hero hero = new Hero();
        Villain villain = new Villain();

        boolean isDefending = false;

        System.out.println("---HERO---");
        hero.create();
        Weapon hw = new Weapon();
        hw.select();
        hero.equip(hw);

        System.out.println("---VILLAIN---");
        villain.create();
        Weapon vw = new Weapon();
        vw.select();
        villain.equip(vw);

        System.out.println("\n---THE BATTLE BEGINS!---\n");
        Scanner sc = new Scanner(System.in);

        while(hero.health > 0 && villain.health > 0)
        {
            System.out.println("\n" + hero.name + ": " + hero.health + " HP | " + villain.name + ": " + villain.health + " HP");

            System.out.println("What do you do?");
            System.out.println("1. Attack with " + hero.equippedWeapon.name);
            System.out.println("2. Defend");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 1)
            {
                System.out.println("You strike " + villain.name + "!");
                villain.health -= hero.equippedWeapon.attack;
            }
            else
            {
                System.out.println("You brace yourself, lowering your profile!");
                isDefending = true;
            }

            if(villain.health > 0)
            {
                System.out.println(villain.name + " retaliates with " + villain.equippedWeapon.name + "!");

                // --- Q-BRANCH CALCULATION UPGRADE ---
                int damageTaken = villain.equippedWeapon.attack;

                if(isDefending)
                {
                    damageTaken /= 2; // Reduce damage by 50%
                    System.out.println("Tactical advantage! Damage reduced to " + damageTaken + ".");
                    isDefending = false; // Reset the shield for the next turn
                }

                hero.health -= damageTaken;
                // ------------------------------------
            }
        }

        if(hero.health >0)
        {
            System.out.println("\nVictory! " + villain.name + " has been defeated!");
            System.out.println("Better luck next time, "+villain.name+".");
        }
        else {
            System.out.println("\nYou were slain by "+villain.name+"!");
            System.out.println("Better luck next time, "+hero.name+".");
        }



    }
}