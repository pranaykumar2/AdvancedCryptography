import java.util.*;
import java.math.BigInteger;

       /*
        @author: pranay kumar
        @aka: kudhiram bose
        @created: March 16, 2024
      */


class Skey {

  BigInteger functions(BigInteger random_number) {
    return random_number.add(random_number).add(BigInteger.valueOf(5)).add(BigInteger.valueOf(19)).add(BigInteger.valueOf(66));
  }

    BigInteger Check_User(String name, BigInteger num, BigInteger compare, int current_index) {

        System.out.print("\n\nEnter User Name: ");
        String check_name;
        Scanner scanner = new Scanner(System.in);
        check_name = scanner.nextLine();
        if (check_name.equals(name)) {
            System.out.print("\nEnter Password(" + current_index + "): ");
            BigInteger password = scanner.nextBigInteger();
            if (functions(password).equals(compare)) {
                System.out.println("User Logged in");
            }
            else {
                System.out.println("Password Already Used\nExiting...");
                System.exit(0);
            }
        }
        else {
            System.out.println("User not found\nExiting...");
            System.exit(0);
        }

      return BigInteger.valueOf(0);
    }

    public static void main(String[] args) {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println(
        "-------------------------------------\n"+
        "This is a Simple SKEY implementation\n"+
        "-------------------------------------"
        );

      String name;
      int random_number;
      BigInteger[] arr = new BigInteger[101];
      BigInteger[] arr_track = new BigInteger[101];
      Scanner scanner = new Scanner(System.in);

      System.out.print("\nEnter your name: ");
      name = scanner.nextLine();

      System.out.print("Enter random number: ");
      random_number = scanner.nextInt();

      BigInteger randomBigInt = BigInteger.valueOf(random_number);

      for (int i = 0; i < 101; i++) {
        arr[i] = new Skey().functions(randomBigInt);
        randomBigInt = arr[i];
      }

      for (int i = 0; i < 101; i++) {
        System.out.println((i+1)+". "+arr[i]);
      }
      Skey skeyInstance = new Skey();

      for(int i = 0; i < 100; i++) {
          System.out.print(
            "\n\n           -------------------\n"+
            "           |     loop " + (i+1) + "      |\n"+
            "           -------------------\n"
          );
          int j = 99 - i;
          skeyInstance.Check_User(name, arr[j], arr[j+1], j+1);
        }

    }
}
