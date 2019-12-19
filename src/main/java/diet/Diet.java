package diet;


import diet.Food.Category;
import diet.Food.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Diet implements IDiet {

  private static int AVERAGE_CALORIES; // Calories average
  private HashMap<String, Food> foods = new HashMap<>();

  public Diet(int averageCalories) {
    AVERAGE_CALORIES = averageCalories; // instantiation of the average calories
    foods.put("fruit", new Food(Type.fruit, Category.carbohydrates, 150));
    foods.put("vegetable", new Food(Type.vegetable, Category.carbohydrates, 100));
    foods.put("seafood", new Food(Type.seafood, Category.proteins, 200));
    foods.put("egg", new Food(Type.egg, Category.proteins, 50));
    foods.put("cheese", new Food(Type.cheese, Category.carbohydrates, 50));
    foods.put("peanut", new Food(Type.peanut, Category.fats, 50));
  }

  @Override
  public int calculCalories(String meal) {
    String[] mealList = meal.split(","); // We split the meal into an array of food
    int nbrCalories = 0;

    for (String foodName : mealList) {
      nbrCalories += foods.get(foodName).getCalories();
    }
    return nbrCalories;
  }

  @Override
  public String evaluateCalories(String meal) {
    int nbrCalories = calculCalories(meal);
    // we compare the number of calories with the average and we return the adequate string
		if (nbrCalories == AVERAGE_CALORIES) {
			return "good, number of calories as expected";
		}
		if (nbrCalories < AVERAGE_CALORIES) {
			return "still " + (AVERAGE_CALORIES - nbrCalories) + " calories to be added";
		} else {
			return "still " + (nbrCalories - AVERAGE_CALORIES) + " calories to be removed";
		}
  }

  @Override
  public String evaluateNutrients(String meal) {
    String[] mealList = meal.split(",");

    Food food;
		List<Category> categories = new ArrayList<>(Arrays.asList(Category.values()));
		HashMap<Category, Boolean>  containedCategories = new HashMap<>();
    for (String foodName : mealList) {
      food = foods.get(foodName);
      for(Category category : categories) {
      	if(food.getCategory() == category) {
					containedCategories.put(category, true);
				}
			}
    }

    String missingCategories = new String();
    boolean isCategoryMissing = false;
		for(Category category : categories) {
			if(!containedCategories.containsKey(category)) {
				if(!isCategoryMissing) {
					missingCategories += category.name();
				} else {
					missingCategories += " and " + category.name();
				}
				isCategoryMissing = true;
			}
		}

		if(isCategoryMissing) {
			return "your meal doesn't contain " + missingCategories + "!";
		} else {
			return "good, your meal contains all required nutrients!";
		}
  }

}