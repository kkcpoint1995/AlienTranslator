import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type n and enter to terminate application \n");

        // infinite loop wait for input
        while (true){
            int humanNumber;

            System.out.print("Enter an Alien numeral: ");
            String userInput = scanner.next();

            if(userInput.equals("n")) break; // break loop and terminate program

            humanNumber = AlienNumbersTraslator(userInput); // translate algorithm

            // input correct handler
            if(humanNumber != 0){
                System.out.println("Alien number:"+ userInput + " translated to number: " + humanNumber);
            } else {
                System.out.println("Alien number not correct");
            }
        }
        scanner.close();
    }

    private static int AlienNumbersTraslator(String userInput){
        // easy to access alien number
        HashMap<Character, Integer> AlienMapNumbers = new HashMap<Character, Integer>() {{
            put('A', 1);
            put('B', 5);
            put('Z', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('R', 1000);
        }};

        int result = 0;
        ArrayList<Integer> sumArray = new ArrayList<>();
        Character previousChar = '\0'; // the left side character of current letter
        userInput = userInput.toUpperCase();

        for (Character currChar : userInput.toCharArray()){

            // kick out function if current char not in alien char
            if(!AlienMapNumbers.containsKey(currChar)) {
                return 0;
            }

            if(previousChar == '\0'){
                // set to current letter and skip because we don't know when to sum yet
                previousChar = currChar;
                continue;
            }

            int previousCharNum = AlienMapNumbers.get(previousChar);
            int currCharNum = AlienMapNumbers.get(currChar);

            if(previousCharNum < currCharNum){
                int sumResult = currCharNum - previousCharNum;
                sumArray.add(sumResult); // append to sum later
                // set empty wait for next to letter because we got sum result
                previousChar = '\0';
            }else if(previousCharNum >= currCharNum){
                sumArray.add(previousCharNum); // append to sum later
                // set new left side letter because we don't know when to sum yet
                previousChar = currChar;
            }
        }


        if(previousChar != '\0'){
            // loop ended, and now we know is last letter so append to sum list
            sumArray.add(AlienMapNumbers.get(previousChar));
        }

        for (int sum : sumArray){
            result += sum;
            //System.out.println("result :"+ result + " from sum " + sum);
        }

        return result;
    }

}