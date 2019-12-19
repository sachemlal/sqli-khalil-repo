package diet;

public class Food {

  public enum Type {
    fruit,
    vegetable,
    seafood,
    egg,
    cheese,
    peanut
  }

  public enum Category {
    carbohydrates,
    proteins,
    fats,
  }

  private Type type;
  private Category category;
  private int calories;

  public Food(Type type, Category category, int calories) {
    this.type = type;
    this.category = category;
    this.calories = calories;
  }

  public Type getType() {
    return type;
  }

  public Category getCategory() {
    return category;
  }

  public int getCalories() {
    return calories;
  }
}

