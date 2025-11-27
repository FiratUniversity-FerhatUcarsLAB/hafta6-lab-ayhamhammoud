//Adi SoyAdi : Ayham Nawaf Hammoud//
//OgrenciNo: 240541613 //

public class OgrenciNotSistemi {

    // 1) Ortalama hesaplama
    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // 2) Geçme durumu
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // 3) Harf notu
    public static String getLetterGrade(double ort) {
        if (ort >= 90) return "A";
        else if (ort >= 80) return "B";
        else if (ort >= 70) return "C";
        else if (ort >= 60) return "D";
        else return "F";
    }

    // 4) Onur listesi (ortalama ≥ 85 ve hiçbir not < 70 olmamalı)
    public static boolean isHonorList(double ort, double vize, double fin, double odev) {
        return (ort >= 85 && vize >= 70 && fin >= 70 && odev >= 70);
    }

    // 5) Bütünleme hakkı (40 ≤ ort < 50)
    public static boolean hasRetakeRight(double ort) {
        return ort >= 40 && ort < 50;
    }

    // Rapor oluşturma (zorunlu değil ama proje için gerekli)
    public static void generateReport(double vize, double fin, double odev) {

        double ort = calculateAverage(vize, fin, odev);
        String letter = getLetterGrade(ort);
        boolean passed = isPassingGrade(ort);
        boolean honor = isHonorList(ort, vize, fin, odev);
        boolean butunleme = hasRetakeRight(ort);

        System.out.println("=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu : " + vize);
        System.out.println("Final Notu : " + fin);
        System.out.println("Odev Notu : " + odev);
        System.out.println("------------------------------");
        System.out.printf("Ortalama : %.1f\n", ort);
        System.out.println("Harf Notu : " + letter);
        System.out.println("Durum : " + (passed ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (honor ? "EVET" : "HAYIR"));
        System.out.println("Butunleme : " + (butunleme ? "VAR" : "YOK"));
    }

    // Örnek test
    public static void main(String[] args) {

        // Örnek giriş:
        // Vize: 85
        // Final: 90
        // Ödev: 88

        generateReport(70, 82, 86);
    }
}

