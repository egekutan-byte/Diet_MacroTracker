import java.util.*;
import java.io.*;

class Food{
    private String foodName;
    private int calorie;
    private double carbo;
    private double protein;
    private double fat;

    public Food(String foodName, int calorie, double carbo, double protein,double fat) {
        this.foodName = foodName;
        this.calorie = calorie;
        this.carbo = carbo;
        this.protein = protein;
        this.fat=fat;
    }

    public String getFoodName() {return foodName;}
    public int getCalorie() {return calorie;}
    public double getCarbo() {return carbo;}
    public double getProtein() {return protein;}
    public double getFat() {return fat;}

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", calorie=" + calorie +
                ", carbo=" + carbo +
                ", protein=" + protein +
                '}';
    }

    public String toCSV(){
        return foodName+","+calorie+","+carbo+","+protein+","+fat;
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

    public void saveToFile(String fileName){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));

            for(Food f:this.Foodlist){
                writer.write(f.toCSV());
                writer.newLine();
            }

            writer.close();
            System.out.println("The datas has been stored.");
        }catch(IOException e){
            System.err.println("An error has occured while writing to the file!");
        }
    }

    public void LoadFromFile(String fileName){

        try{
            File file=new File(fileName);
            if (!file.exists()) {
                System.out.println("Henüz kayıtlı bir dosya yok, temiz sayfa açılıyor.");
                return;
            }

            Scanner sc=new Scanner(file);

            while(sc.hasNextLine()){
                String line= sc.nextLine();

                String[] parts=line.split(",");

                String foodName=parts[0];
                int calories=Integer.parseInt(parts[1]);
                double carbo=Double.parseDouble(parts[2]);
                double protein=Double.parseDouble(parts[3]);
                double fat=Double.parseDouble(parts[4]);

                Food f=new Food(foodName,calories,carbo,protein,fat);
                this.Foodlist.add(f);
            }
            sc.close();

        }catch(Exception e){
            System.err.println("An error has occured during the reading process!");
        }finally{
            System.out.println("The datas has been loaded to the file.");
        }
    }

    public void resetData(String fileName) {
        // 1. Listeyi temizle (RAM'den sil)
        this.Foodlist.clear();

        // 2. Dosyayı sil (Hard Diskten sil)
        File file = new File(fileName);
        if (file.exists()) {
            file.delete(); // Dosyayı fiziksel olarak siler
            System.out.println(Renk.YESIL + ">>> Tüm veriler başarıyla sıfırlandı!" + Renk.RESET);
        } else {
            System.out.println(Renk.SARI + ">>> Zaten silinecek bir dosya yok." + Renk.RESET);
        }
    }
// DietManager class'ının içine ekle:

    public void printList() {
        if (this.Foodlist.isEmpty()) {
            System.out.println(Renk.SARI + "Listeniz şu an boş. Biraz yemek ekleyin!" + Renk.RESET);
        } else {
            System.out.println(Renk.CYAN + "\n----------------------------------------------------------------" + Renk.RESET);
            // %-20s: Sola dayalı 20 karakterlik boşluk ayır (İsim için)
            // %10s: Sağa dayalı 10 karakterlik boşluk ayır (Sayılar için)
            System.out.printf("%-20s | %10s | %10s | %10s | %10s%n", "YEMEK ADI", "KALORİ", "KARB(g)", "PRO(g)", "YAĞ(g)");
            System.out.println(Renk.CYAN + "----------------------------------------------------------------" + Renk.RESET);

            for (Food f : this.Foodlist) {
                System.out.printf("%-20s | %10d | %10.1f | %10.1f | %10.1f%n",
                        f.getFoodName(),
                        f.getCalorie(),
                        f.getCarbo(),
                        f.getProtein(),
                        f.getFat()); // Getter eklemen gerekebilir (getFat)
            }
            System.out.println(Renk.CYAN + "----------------------------------------------------------------" + Renk.RESET);
        }
    }
    // Bu metot, mevcut durum ve hedefe göre bir çubuk çizer
    public void showProgressBar(int current, int target) {
        int totalBars = 30; // Çubuğun uzunluğu (karakter sayısı)
        double percentage = (double) current / target;

        // Yüzde 100'ü geçerse patlamasın diye sınırla
        if (percentage > 1) percentage = 1;

        int filledBars = (int) (totalBars * percentage);
        int emptyBars = totalBars - filledBars;

        System.out.print("Günlük Hedef: [");

        // Dolu kısımlar (Yeşil #)
        System.out.print(Renk.YESIL);
        for (int i = 0; i < filledBars; i++) System.out.print("#");

        // Boş kısımlar (Kırmızı -)
        System.out.print(Renk.KIRMIZI);
        for (int i = 0; i < emptyBars; i++) System.out.print("-");

        System.out.println(Renk.RESET + "] %" + (int)(percentage * 100));
    }
    public void searchFood(String keyword) {
        System.out.println("\n>>> ARAMA SONUÇLARI: " + keyword);
        boolean bulunduMu = false;

        // Listenin tamamını gez
        for (Food f : this.Foodlist) {
            // contains: İçinde geçiyor mu? (Büyük/küçük harf duyarlılığını kaldırmak için toLowerCase yaptık)
            if (f.getFoodName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("- " + f.toString()); // Veya tablo formatında yazdırabilirsin
                bulunduMu = true;
            }
        }

        if (!bulunduMu) {
            System.out.println(Renk.KIRMIZI + "Hiçbir sonuç bulunamadı." + Renk.RESET);
        }
    }
}