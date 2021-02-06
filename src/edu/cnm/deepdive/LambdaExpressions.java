package edu.cnm.deepdive;

import java.util.ArrayList;
import java.util.List;

class Animal {
  private String type;
  private boolean canJump;
  private boolean canSwim;

  public Animal(String type, boolean canJump, boolean canSwim) {
    this.type = type;
    this.canJump = canJump;
    this.canSwim = canSwim;
  }

  public boolean canJump() {
    return canJump;
  }

  public boolean canSwim() {
    return canSwim;
  }

  public String getType() {
    return type;
  }


}

interface CheckAnimal {
  boolean check(Animal animal);
}

class CheckCanJump implements CheckAnimal{

  @Override
  public boolean check(Animal animal) {
    return animal.canJump();
  }
}

public class LambdaExpressions {

  public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    animals.add(new Animal("fish", false, true));
    animals.add(new Animal("rabbit", true, false));
    animals.add(new Animal("dog", true, true));

    print(animals, new CheckCanJump());

    print(animals, animal -> animal.canSwim());
    //if we want to print the animals that cannot swim:
    print(animals, animal -> !animal.canSwim());


  }
//if we now want to check which animals can swim we need to write another class CheckCanSwim that
//implements the interface. IN case we need to check on other filters, we have to write multiple
//methods.
//This is where the lambda expression comes in. See line 54 for the canSwim().
  private static void print(List<Animal> animals, CheckAnimal filter) {
    for (Animal animal : animals) {
      if(filter.check(animal)) {
        System.out.println(animal.getType());
      }
    }
    System.out.println();
  }

}
