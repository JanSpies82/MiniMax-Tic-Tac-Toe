import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scannerInstance = null;
    
    private ScannerSingleton() {}
    
    public static Scanner getInstance() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }
}
