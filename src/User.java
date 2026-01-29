public class User {
    public static void main(String[] args) {
        DietManager manager1=new DietManager();
        Food breakfast=new Food("Simit",100,10,2);
        Food lunch=new Food("Tavuk Pilav",500,150,40);

        manager1.addFood(breakfast);
        manager1.addFood(lunch);

        System.out.println("Total calories: "+manager1.CalculateTotalCalories());

    }
}
