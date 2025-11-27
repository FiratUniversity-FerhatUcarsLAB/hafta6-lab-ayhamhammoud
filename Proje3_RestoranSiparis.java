//Adi SoyAdi : Ayham Nawaf Hammoud//
//OgrenciNo: 240541613 //



public class RestoranSiparis {

    // 1. Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2. Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;
        }
    }

    // 3. İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4. Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5. Combo kontrolü (ana + içecek + tatlı seçili olmalı)
    public static boolean isComboOrder(int ana, int icecek, int tatli) {
        return (ana > 0 && icecek > 0 && tatli > 0);
    }

    // 6. Happy Hour kontrolü (14:00 – 17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7. İndirim hesaplama (bütün indirimler burada)
    public static double calculateDiscount(
            double tutar,
            boolean combo,
            boolean ogrenci,
            int saat,
            int gun,
            double icecekFiyati)
    {

        double toplamIndirim = 0.0;

        // --- Combo %15
        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        // --- Happy Hour (sadece içecekte %20)
        if (isHappyHour(saat) && icecekFiyati > 0) {
            toplamIndirim += icecekFiyati * 0.20;
        }

        // --- Öğrenci indirimi (sadece hafta içi)
        boolean haftaIci = (gun >= 1 && gun <= 5);
        if (ogrenci && haftaIci) {
            toplamIndirim += (tutar - toplamIndirim) * 0.10;
        }

        // --- 200 TL üzeri %10 indirimi (EN SON uygulanır)
        double araSon = tutar - toplamIndirim;
        if (araSon > 200) {
            toplamIndirim += araSon * 0.10;
        }

        return toplamIndirim;
    }

    // 8. Servis bahşişi (%10)
    public static double calculateServiceTip(double toplam) {
        return toplam * 0.10;
    }

    // Ticket info (zorunlu değil, kolay kullanım için)
    public static String generateOrderInfo(
            int ana, int bas, int icecek, int tatli,
            int saat, boolean ogrenci, int gun)
    {

        double fAna = getMainDishPrice(ana);
        double fBas = getAppetizerPrice(bas);
        double fIce = getDrinkPrice(icecek);
        double fTat = getDessertPrice(tatli);

        double araToplam = fAna + fBas + fIce + fTat;

        boolean combo = isComboOrder(ana, icecek, tatli);

        double indirim = calculateDiscount(araToplam, combo, ogrenci, saat, gun, fIce);

        double toplam = araToplam - indirim;

        double bahsis = calculateServiceTip(toplam);

        return  "===== RESTORAN FİŞİ =====\n" +
                "Ara Toplam: " + araToplam + " TL\n" +
                "Toplam İndirim: -" + String.format("%.2f", indirim) + " TL\n" +
                "Ödenecek: " + String.format("%.2f", toplam) + " TL\n" +
                "Bahşiş Önerisi (%10): " + String.format("%.2f", bahsis) + " TL\n" +
                "==========================";
    }

    // Test
    public static void main(String[] args) {

        // ÖRNEK SENARYO
        // Ana Yemek = 2 (120 TL)
        // Başlangıç = 2 (45 TL)
        // İçecek = 3 (35 TL)
        // Tatlı = 1 (65 TL)
        // Saat = 15 (Happy Hour)
        // Öğrenci = true
        // Gün = 3 (Çarşamba)

        System.out.println(
                generateOrderInfo(4, 1, 4, 2, 10, true, 3)
        );
    }
}


