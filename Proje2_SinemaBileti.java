//Adi SoyAdi : Ayham Nawaf Hammoud//
//OgrenciNo: 240541613 //



public class SinemaBilet {

    // 1. Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    // 2. Matine kontrolü (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3. Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {

        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;       // Hafta içi matine
        if (!weekend && !matinee) return 65;      // Hafta içi normal
        if (weekend && matinee) return 55;        // Hafta sonu matine
        return 85;                                // Hafta sonu normal
    }

    // 4. İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        // Yaş indirimi her zaman önce kontrol
        if (yas >= 65) return 0.30;
        if (yas < 12) return 0.25;

        double discount = 0.0;

        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) {
                    discount = 0.20;  // Pzt–Perş
                } else {
                    discount = 0.15;  // Cuma–Pazar
                }
                break;

            case 2: // Öğretmen
                if (gun == 3) {
                    discount = 0.35; // Çarşamba
                }
                break;

            case 3:
                discount = 0.0;
                break;
        }

        return discount;
    }

    // 5. Film format ücreti
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6. Toplam fiyat hesaplama
    public static double calculateFinalPrice(
            int gun, int saat, int yas, int meslek, int filmTuru)
    {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);

        double discounted = base - (base * discountRate);
        return discounted + formatExtra;
    }

    // 7. Bilet yazdırma
    public static String generateTicketInfo(
            int gun, int saat, int yas, int meslek, int filmTuru)
    {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        return  "===== SİNEMA BİLETİ =====\n" + "Gün: " + gun + "\n" + "Saat: " + saat + "\n" + "Yaş: " + yas + "\n" +
                "Meslek: " + meslek + "\n" +
                "Format ücreti: " + formatExtra + " TL\n" +
                "Temel fiyat: " + base + " TL\n" +
                "İndirim oranı: %" + (discountRate * 100) + "\n" +
                "Toplam fiyat: " + finalPrice + " TL\n";
    }

    // TEST (main)
    public static void main(String[] args) {

        // Örnek senaryo:
        // Gün: 4 (Perşembe)
        // Saat: 10 (matine)
        // Yaş: 22
        // Meslek: 1 (Öğrenci)
        // Film türü: 2 (3D)

        String ticket = generateTicketInfo(6, 10, 22, 3, 4);
        System.out.println(ticket);
    }
}

