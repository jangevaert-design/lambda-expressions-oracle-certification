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

class CheckCanJump implements CheckAnimal {

  @Override
  public boolean check(Animal animal) {
    return animal.canJump();
  }
}

interface AnotherCheck {

  boolean check(Animal first, Animal second);
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

    print(animals, (Animal a) -> {//we use curly braces here because we want to make this a code
      //block unlike the lambdas on line 54 and 56 who only have one method.
//      Animal a = new Animal("cat", true, false); will not compile because you create a new Animal
//      with the same name.
      System.out.println("checking animal = " + a.getType());
      return a.canJump();// return is required because we have more than one statement in the body.
    });

    Animal fish = animals.get(0);
    Animal rabbit = animals.get(1);
    Animal dog = animals.get(2);

    print(fish, rabbit, (first, second) -> first.canJump() && second.canSwim());
  }

  private static void print(Animal first, Animal second, AnotherCheck check) {
    System.out.println(check.check(first, second));
  }

  //if we now want to check which animals can swim we need to write another class CheckCanSwim that
//implements the interface. IN case we need to check on other filters, we have to write multiple
//methods.
//This is where the lambda expression comes in. See line 54 for the canSwim().
  private static void print(List<Animal> animals, CheckAnimal filter) {
    for (Animal animal : animals) {
      if (filter.check(animal)) {
        System.out.println(animal.getType());
      }
    }
    System.out.println();
  }

}
