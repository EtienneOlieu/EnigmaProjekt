import java.util.Arrays;
import java.util.Scanner;

public class EnigmaProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        String mode = "";
        System.out.println("Welcome to the CRYPTATRON 5000 (patent pending)!");
        System.out.println("The CRYPTATRON 5000 (patent pending), encodes or decodes messages according to three different known cyphers.");


        while (userChoice != 4) {
            System.out.println("The CRYPTATRON 5000 (patent pending) demands that you make your choice below:");
            System.out.println("Choose; NUMBER CYPHER [1], CAESAR CYPHER [2], VIGENÈRE CYPHER[3], or QUIT[4]?");
            userChoice = scanner.nextInt();

            if (userChoice == 1) {
                System.out.println("You have chosen the NUMBER CYPHER.");
                System.out.println("You must either encrypt[e] or decrypt[d]!");
                mode = scanner.next();
                mode = mode.substring(0, 1);

                if (mode.equals("e")) {
                    System.out.println("You will encrypt a piece of text!");
                    System.out.println("Supply the text now.");
                    String encryptedWord = encodeNumberCypher(mode);
                    System.out.println("The word has now been encrypted to:");
                    System.out.println(encryptedWord+"\n");

                } else {
                    if (mode.equals("d")) {
                        System.out.println("You will decrypt a string of numbers, supplied as an array!");
                        System.out.println("Supply the numbers now. Format is; some form of a bracket ['(','{', or '['] with the numbers separated by commas [',']");
                        String decryptedWord = decodeNumberCypher(mode);
                        System.out.println(decryptedWord);
                    } else {
                        System.out.println("Your choice was not registered");
                    }
                }
            }

            if (userChoice == 2) {
                System.out.println("You have chosen the CAESAR CYPHER.");
                System.out.println("You must either encrypt[e] or decrypt[d]!");
                mode = scanner.next();
                mode = mode.substring(0, 1);

                if (mode.equals("e")) {
                    System.out.println("You will encrypt a piece of text!");

                    String encryptedCaesar = caesarEncrypt(mode);
                    System.out.println("Your message has been encrypted to: ");
                    System.out.println(encryptedCaesar);

                } else {
                    if (mode.equals("d")) {
                        System.out.println("You will decrypt a piece of text");
                        String decryptedCaesar = caesarDecrypt(mode);
                        System.out.println("Your message has been decrypted to:");
                        System.out.println(decryptedCaesar);


                    } else {
                        System.out.println("Your choice was not registered");
                    }
                }
            }
            if (userChoice == 3){
                System.out.println("You have chosen the VIGENÈRE CYPHER.");
                System.out.println("You must either encrypt[e] or decrypt[d]!");
                mode = scanner.next();
                mode = mode.substring(0, 1);

                if (mode.equals("e")){
                    String encryptedVigenereComplete = encryptedVigenere(mode);
                    System.out.println("Your message was encrypted to:");
                    System.out.println(encryptedVigenereComplete);

                } else {
                    if (mode.equals("d")){
                        String decryptedVigenereComplete = decryptVigenere(mode);
                        System.out.println("Your message was decrypted to:");
                        System.out.println(decryptedVigenereComplete);


                    }else {
                        System.out.println("Your choice was not registered");

                    }
                }

            }
        }
        System.out.println("Goodbye!");
        System.out.println("The CRYPTATRON 5000 (patent pending) thanks you, for putting it to good use.");
    }


    public static String encodeNumberCypher(String e) {
        Scanner scanner = new Scanner(System.in);
        String plainText = "";
        plainText = scanner.nextLine();

        plainText = plainText.toUpperCase();
        int[] encodedText = textToListOfNumbers(plainText);
        String encodedTextOut = Arrays.toString(encodedText);

        return encodedTextOut;
    }

    public static int[] textToListOfNumbers(String text) {
        int[] numbers = new int[text.length()];
        char[] letters = text.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            numbers[i] = letterToNumber(letters[i]);

        }
        System.out.println(Arrays.toString(numbers));//skal væk
        return numbers;
    }

    public static int letterToNumber(char letter) {
        //giver et tal til et bogstav
        String alfabetet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        int num = alfabetet.indexOf(letter);
        return num;
    }

    public static String decodeNumberCypher(String d) {
        //giver et tal et bogstav
        Scanner scanner = new Scanner(System.in);
        String numbersInAString = scanner.nextLine();

        int[] numbers = getIntArrayFromString(numbersInAString);

        String plaintext = listOfNumbersToText(numbers);
        return plaintext;
    }

    public static int[] getIntArrayFromString(String commaSeparatedNumbers) {
        String[] numberStrings = commaSeparatedNumbers.substring(1, commaSeparatedNumbers.length() - 1).split(",");
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i].trim());
        }
        return numbers;
    }

    public static char numberToLetter(int number) {
        //giver et tal et bogstav
        String alfabetet = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
        char letter = alfabetet.charAt(number);
        return letter;
    }

    public static String listOfNumbersToText(int[] numbers) {
        String text = "";
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

            char letter = numberToLetter(number);

            text = text + letter;

        }
        return text;

    }

    public static int shiftNumber (int number, int shiftValue) {
        if (number != 0) {
            //med hjælp fra Graham, jeg takker ydmygst
            shiftValue = Math.abs(shiftValue) % 30;
            number = (number + shiftValue) % 30;
            }
        return number;
    }



    public static int[] shiftListOfNumbers (int[] numbers, int shiftValue){

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = shiftNumber(numbers[i],shiftValue);
        }
            return numbers;

    }

    public static String caesarEncrypt (String e){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Provide the text to be encrypted!");
    String plainText = scanner.nextLine();
    plainText = plainText.toUpperCase();
        System.out.println(plainText);//skal fjernes
    System.out.println("Now, provide a shift-value for the CRYPTATRON 5000 (patent pending) to do its job!");
    int shiftValue = scanner.nextInt();
        System.out.println(shiftValue);//skal fjernes
    //laver indledningsvis stringen om til et array.
    int[] caesarEncryptNumbers = textToListOfNumbers(plainText);

      //nu har den lavet tallene om til et array der er skiftet et antal gange, svarende til shiftværdi
      int[] caesarEncryptNumbersPostShift = shiftListOfNumbers(caesarEncryptNumbers,shiftValue);

      String encryptedCaesar = listOfNumbersToText(caesarEncryptNumbersPostShift);

        return encryptedCaesar;

    }

    public static String caesarDecrypt (String d){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the text to be decrypted!");
        String encryptedText = scanner.nextLine();
        encryptedText = encryptedText.toUpperCase();
        System.out.println("Now, provide a shift-value for the CRYPTATRON 5000 (patent pending) to do its job!");
        int shiftValue = scanner.nextInt();
        shiftValue= shiftValue %30;
        shiftValue = shiftValue -(shiftValue+shiftValue)+30;

        int[] caesarEncryptNumbers = textToListOfNumbers(encryptedText);
        int[] caesarEncryptNumbersPostShift = shiftListOfNumbers(caesarEncryptNumbers,shiftValue);
        String decryptedCaesar = listOfNumbersToText(caesarEncryptNumbersPostShift);
        return decryptedCaesar;

    }

    public static String encryptedVigenere (String e){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the text to be encrypted!");
        String plainText = scanner.nextLine();
        plainText = plainText.toUpperCase();
        System.out.println("Now you must give the CRYPTATRON 5000 (patent pending) a code word, for it to do its job.");
        String codeWord = scanner.nextLine();
        codeWord = codeWord.toUpperCase();

        int [] vigenereNumbers = textToListOfNumbers(plainText);
        int [] codeWordNumbers = textToListOfNumbers(codeWord);

        int[] scrambledVigenereNumbers = shiftListOfNumbersVigenere(vigenereNumbers,codeWordNumbers);

        String encodedVigenere = listOfNumbersToText(scrambledVigenereNumbers);
        return encodedVigenere;

    }

    public static int[] shiftListOfNumbersVigenere (int[] numbers, int [] shiftValues){
        //stjålet fra dig Peter
        int pos = 0;
        int shiftValue = 0;

        for (int i = 0; i < numbers.length; i++){
            shiftValue = shiftValues[pos];
            numbers[i] = shiftNumber(numbers[i],shiftValue);
            pos++;
            if (pos == shiftValues.length){
                pos = 0;
            }

        }
        return numbers;

    }

    public static String decryptVigenere (String d){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide the text to be decrypted!");
        String plainText = scanner.nextLine();
        plainText = plainText.toUpperCase();
        System.out.println("Now you must give the CRYPTATRON 5000 (patent pending) a code word, for it to do its job.");
        String codeWord = scanner.nextLine();
        codeWord = codeWord.toUpperCase();

        int[] vigenereNumbers = textToListOfNumbers(plainText);
        int[] codeWordNumbers = textToListOfNumbers(codeWord);

        int[] deScrambledVigenereNumbers = shiftListOfNumbersDecryptVigenere(vigenereNumbers,codeWordNumbers);
        String decodedVigenere = listOfNumbersToText(deScrambledVigenereNumbers);
        return decodedVigenere;
    }
    public static int[] shiftListOfNumbersDecryptVigenere (int[] numbers, int[] shiftValues){

        int pos = 0;
        int shiftValue = 0;

        for (int i = 0; i < numbers.length; i++){
            shiftValue = shiftValues[pos];
            shiftValue = shiftValue -(shiftValue+shiftValue)+30;
            numbers[i] = shiftNumber(numbers[i],shiftValue);

            pos++;
            if (pos == shiftValues.length){
                pos = 0;
            }

        }
        return numbers;


    }
}
