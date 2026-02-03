import java.util.*;

class Renk {
    public static final String RESET = "\u001B[0m";
    public static final String KIRMIZI = "\u001B[31m";
    public static final String YESIL = "\u001B[32m";
    public static final String SARI = "\u001B[33m";
    public static final String MAVI = "\u001B[34m";
    public static final String CYAN = "\u001B[36m"; // Turkuaz
}



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DietManager manager = new DietManager();
        manager.LoadFromFile("diyet_verisi.txt");

        // ASCII ART LOGO (Havalı Giriş)
        System.out.println(Renk.MAVI + "#############################################");
        System.out.println("#      EGE'S DIET TRACKER v1.0 (JAVA)       #");
        System.out.println("#      ------------------------------       #");
        System.out.println("#      [Eat] -> [Track] -> [Repeat]         #");
        System.out.println("#############################################" + Renk.RESET);

        while (true) {
            System.out.println("\n" + Renk.SARI + "=== ANA MENÜ ===" + Renk.RESET);
            System.out.println("1. Yemek Ekle");
            System.out.println("2. Listeyi Göster (Tablo)");
            System.out.println("3. Toplam Kalori");
            System.out.println("4. Verileri Sıfırla (Reset)");
            System.out.println("5. Kaydet ve Çık");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Bug fix

            switch (secim) {
                case 1:
                    System.out.print("Yemek Adı: ");
                    String ad = scanner.nextLine();
                    System.out.print("Kalori: ");
                    int cal = scanner.nextInt();
                    System.out.print("Karbonhidrat: ");
                    double carb = scanner.nextDouble();
                    System.out.print("Protein: ");
                    double prot = scanner.nextDouble();
                    System.out.print("Yağ: ");
                    double fat = scanner.nextDouble();

                    manager.addFood(new Food(ad, cal, carb, prot, fat));
                    // Başarılı mesajını yeşil yapalım (addFood içinde değiştirebilirsin)
                    break;
                case 2:
                    manager.printList();
                    break;
                case 3:
                    int total = manager.CalculateTotalCalories();
                    int gunlukHedef = 2500; // İstersen bunu da kullanıcıdan alabilirsin

                    System.out.println("\n>>> KALORİ ANALİZİ");
                    manager.showProgressBar(total, gunlukHedef); // <-- ÇUBUĞU ÇAĞIR

                    System.out.println("Alınan: " + total + " / Hedef: " + gunlukHedef);
                    break;
                case 4:
                    System.out.print(Renk.KIRMIZI + "Tüm veriler silinecek! Emin misin? (e/h): " + Renk.RESET);
                    String onay = scanner.next();
                    if(onay.equalsIgnoreCase("e")) {
                        manager.resetData("diyet_verisi.txt");
                    } else {
                        System.out.println("İşlem iptal edildi.");
                    }
                    break;
                case 5:
                    manager.saveToFile("diyet_verisi.txt");
                    System.out.println(Renk.MAVI + "Görüşmek üzere şampiyon!" + Renk.RESET);
                    break;
                case 6:
                    System.out.print("Aranacak kelimeyi girin: ");
                    String kelime = scanner.nextLine();
                    manager.searchFood(kelime);
                    return;
                default:
                    System.out.println(Renk.KIRMIZI + "Geçersiz seçim!" + Renk.RESET);
            }
        }
    }
}