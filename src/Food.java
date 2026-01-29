import java.util.*;

class Food{
    private String foodName;
    private int calorie;
    private double carbo;
    private double protein;

    public Food(String foodName, int calorie, double carbo, double protein) {
        this.foodName = foodName;
        this.calorie = calorie;
        this.carbo = carbo;
        this.protein = protein;
    }

    public String getFoodName() {return foodName;}
    public int getCalorie() {return calorie;}
    public double getCarbo() {return carbo;}
    public double getProtein() {return protein;}

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", calorie=" + calorie +
                ", carbo=" + carbo +
                ", protein=" + protein +
                '}';
    }
}
class DietManager{
    private ArrayList<Food> Foodlist;

    public DietManager() {
        this.Foodlist =new ArrayList<>();
    }

    public void addFood(Food food){
        this.Foodlist.add(food);
        System.out.println(food.getFoodName()+" has been added to your list.");
    }

    public int CalculateTotalCalories(){
        int total=0;
        for(Food f:this.Foodlist){
            total+=f.getCalorie();
        }
        return total;
    }
}