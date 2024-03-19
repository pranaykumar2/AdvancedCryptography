import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

class SecretSharing extends Thread{
    public static void main(String[] args)  throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(
            "---------------------------------------\n"+
            "This is a Secret Sharing implementation\n"+
            "---------------------------------------"
        );

        Thread thread = new Thread();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nEnter the Message: ");
        String message = scanner.nextLine();
        int length = message.length();

        String R = generateRandomString(length);
        String S = generateRandomString(length);
        String T = generateRandomString(length);

        System.out.println("\nRandom strings: (generating)\n---------------");
        thread.sleep(1000);
        System.out.println("R: " + R);
        thread.sleep(1000);
        System.out.println("S: " + S);
        thread.sleep(1000);
        System.out.println("T: " + T);

        String U = xorStrings(message, R, S, T);

        System.out.println("U: " + U);

        thread.sleep(7000);

        System.out.println(
            "---------------------------------------\n"+
            "Checking any unknown person among us...\n"+
            "---------------------------------------"
        );
        thread.sleep(1000);
        System.out.println("\nReading R");
        thread.sleep(2000);
        System.out.println("Reading S");
        thread.sleep(2000);
        System.out.println("Reading T");
        thread.sleep(2000);
        System.out.println("and Reading U");
        thread.sleep(2000);
        System.out.println("XOR-ing computing message integrity");
        String Mtest = xorStrings(R, S, T, U);
        thread.sleep(1000);
        if(Mtest.equals(message)) {
          System.out.println("Message : "+Mtest);
          thread.sleep(1000);
          System.out.print("Verdict :: ");
          thread.sleep(3000);
          System.out.println("NO INTRUDER FOUND");
        }
        else {
          System.out.println("Message : "+Mtest);
          thread.sleep(1000);
          System.out.print("Verdict :: ");
          thread.sleep(3000);
          System.out.println("INTRUDER FOUND");
        }
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String xorStrings(String message, String R, String S, String T) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
          char xorChar = (char) (message.charAt(i) ^ R.charAt(i) ^ S.charAt(i) ^ T.charAt(i));
          result.append(xorChar);
        }
        return result.toString();
    }
}
