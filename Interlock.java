import java.util.*;

public class Interlock extends Thread{
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Message:> ");
        String message = scanner.nextLine();

        int length = message.length();
        char[] charArray = new char[length];
        char[] cipherArray = new char[length];
        char[] plainArray = new char[length];

        for(int i = 0; i < length; i++) {
          charArray[i] = message.charAt(i);
        }

        int key = 5;
        for(int i = 0; i < length; i++) {
          char ch = charArray[i];
          cipherArray[i] = (char)((ch - 'A' + key) % 26 + 'A');
        }

        String firstCipher, secondCipher;
        if (length % 2 == 0) {
            firstCipher = new String(cipherArray, 0, length / 2);
            secondCipher = new String(cipherArray, length / 2, length / 2);
        } else {
            firstCipher = new String(cipherArray, 0, length / 2 + 1);
            secondCipher = new String(cipherArray, length / 2 + 1, length / 2);
        }

        System.out.println("\nFirst Cipher Array:> " + firstCipher);
        Thread.sleep(1000);
        System.out.println("\nSecond Cipher Array:> " + secondCipher);
        Thread.sleep(1000);

        /*
          Now Performing Decryption on both halves
        */
        for(int i = 0; i < length; i++){
          char ch = cipherArray[i];
          plainArray[i] = (char)((ch - 'A' - key + 26) % 26 + 'A');
      	}

        String firstPlain, secondPlain;
        if (length % 2 == 0) {
            firstPlain = new String(plainArray, 0, length / 2);
            secondPlain = new String(plainArray, length / 2, length / 2);
        } else {
            firstPlain = new String(plainArray, 0, length / 2 + 1);
            secondPlain = new String(plainArray, length / 2 + 1, length / 2);
        }

        System.out.println("\nFirst Plain Array:> " + firstPlain);
        Thread.sleep(1000);
        System.out.println("\nSecond Plain Array:> " + secondPlain);
        Thread.sleep(1000);
        String decryptedText = firstPlain+secondPlain;
        System.out.println("Decrypted message :> "+ decryptedText);

    }
}
