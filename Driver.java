package studyroom;
//Author:Justin and Angel

import java.util.Scanner;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;


public class Driver {

    public static void main(String[] args) {

        // Input and Collections CREATION
        HashMap <Integer,Student> s = new HashMap();
        Queue <Room> r = new LinkedList<>();
        HashMap <String, Books> b = new HashMap();
        Scanner input = new Scanner(System.in);
        // Call Menu
        logInMenu(input,s,r,b);
    }

    public static void logInMenuInfo() {
        System.out.println("\n ----- LOG IN -----");
        System.out.println(" --- Welcome to the Wilfred J. Airey Library at Norco College ---");
        System.out.println(" --- How may we help you today? ---");
        System.out.println(" --- Press #1 to Log In.");
        System.out.println(" --- Press #2 to Register as a New User.");
        System.out.println(" --- Press #3 Show sort and search.");
        System.out.print(" --- Select your option:  ");
    }

    public static void logInMenu(Scanner input, HashMap<Integer, Student> s, Queue<Room> r, HashMap<String,Books> b) {
        Integer userOption = null;
        do {
            logInMenuInfo();
            String userOptionString = input.nextLine();
            // Check if user input is NUMERIC
            while (!isNumeric(userOptionString)){
                userOptionString = userOptionInputError(input,userOptionString);
            }
            userOption = Integer.parseInt(userOptionString);

            // User inputs INVALID Log In option
            if (userOption != 1 && userOption != 2 && userOption != 3) {
                userOptionInputError(input,userOptionString);
            }
            // Logs into Library
            else if (userOption == 1) {
                functionLogIn(input,s,r,b);
            }
            // Registers current user as a New User
            else if (userOption == 2) {
                registerStudent(input,s);
            }
            // Saves and Exits the Program
            else if (userOption == 3) {
                    Student users = new Student();
                    System.out.println(users.addUser("Pita", 852));
                    System.out.println(users.addUser("Beto", 658));
                    System.out.println(users.addUser("Juan", 966));
                    System.out.println(users.addUser("Manda", 10));
                    System.out.println(users.addUser("Pablo", 527));
                    System.out.println();

                    System.out.println("Displaying Users.");
                    System.out.println(users.displayUsers());
                    System.out.println("Displaying users after sorting.");

                    users.sortUsers();
                    System.out.println(users.displayUsers());

                    System.out.println("Searching for user with id: 527");
                    System.out.println("User is : " + users.searchById(527));
                    System.out.println("Searching for user with id: 3000");
                    System.out.println("User is : " + users.searchById(3000));
                           
            }

        } while (userOption != 3);
    }

    public static boolean isNumeric(String strNum) {
        try {
            Integer digit = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String userOptionInputError(Scanner input, String userOptionString) {
        System.out.print("  Select your option :  ");
        userOptionString = input.nextLine();
        return userOptionString;
    }

   

    public static void functionLogIn(Scanner input, HashMap<Integer, Student> s, Queue<Room> r, HashMap<String,Books> b) {
        Integer userStudentID = 0;
        String userStudentIDString = "";
        do {
            System.out.println("\n --- Please enter your Student ID to log in,"
                    + " or enter 'back' to return to the Log In Menu. ---");
            System.out.print("     -- Student ID: ");
            userStudentIDString = input.nextLine();

            // Check if ID input is either a number, 'admin', or 'back
            while (!isNumeric(userStudentIDString) && !userStudentIDString.toLowerCase().equals("admin")
                    && !userStudentIDString.toLowerCase().equals("back")) {
                userStudentIDString = functionStudentIDNotNumeric(input, userStudentIDString);
            }

            // If ID is a number, parse the string as an integer
            if (isNumeric(userStudentIDString)) {
                userStudentID = Integer.parseInt(userStudentIDString);
            }

            // Admin's Log In
            if (userStudentIDString.toLowerCase().equals("admin")) {
                //adminMenu(input,s,r,b);
            }
            // Return to Log In Menu
            else if (userStudentIDString.toLowerCase().equals("back")) {
                return;
            }
            // User has NOT registered their ID into the Library
            else if (!s.containsKey(userStudentID)){
                functionLogInNotRegistered();
            }
            // Student's Log In
            else if (s.containsKey(userStudentID)) {
                studentMenu(input,s,r,b);
            }

        } while (!userStudentIDString.toLowerCase().equals("back"));
    }

    public static void functionLogInNotRegistered() {
        System.out.println(" --- Student ID has not been registered into the Library collection. ---");
        System.out.println(" --- Please return to the Log In Menu to register your Student ID. ---");
    }

    public static String functionStudentIDNotNumeric(Scanner input, String userStudentIDString) {
        System.out.println(" ===== ERROR: Student ID is NUMERIC. Please enter your NUMERIC Student ID. =====");
        System.out.print("  -- Student ID: ");
        userStudentIDString = input.nextLine();
        return userStudentIDString;
    }

    public static void registerStudent(Scanner input, HashMap<Integer,Student> s) {
        Student temp = new Student();

        // New Student ID
        System.out.println("\n --- Please Enter your 7-digit Student ID ---");
        System.out.print("     -- Student ID: ");
        String registerStudentID = input.nextLine();
        while (!isNumeric(registerStudentID)) {
            registerStudentID = functionStudentIDNotNumeric(input, registerStudentID);
        }
        // ----> IMPORTANT: Need to include an ID check, see if it already exists in Hashmap
        // ----> IMPORTANT: IF ID exists, call user to input a different ID
        temp.setIdNumber(Integer.parseInt(registerStudentID));

        // New Student First Name
        System.out.println("\n --- Please Enter your First Name ---");
        System.out.print("     -- First Name: ");
        String registerStudentFirstName = input.nextLine();
        temp.setName(registerStudentFirstName);

        // New Student Last Name
        System.out.println("\n --- Please Enter your Last Name ---");
        System.out.print("     -- Last Name: ");
        String registerStudentLastName = input.nextLine();
        temp.setName(registerStudentLastName);

        s.put(Integer.parseInt(registerStudentID), temp);
        System.out.println("\n --- New Student added to the Library Collection ---");
        System.out.println('\n');
    }

    public static void studentMenu(Scanner input, HashMap<Integer, Student> s, Queue<Room> r, HashMap<String,Books> b) 
    {
        String RserveInput = null;
        
        //---Display promt ---
        System.out.print(" \n");
        System.out.println(" ===== Student's Menu =====");
        System.out.println("\n --- What would you like to do? ---");
        System.out.println("   --- Press #1 for Room Reservation Department.");
        System.out.println("   --- Press #2 for Book Department.");
        System.out.println("   --- Press #3 to return to Log In Menu");
        System.out.println("   --- Select your option:  ");

        int departmentOption = input.nextInt();

        // Go to ROOM view-AVAILABLE
        switch (departmentOption)
        {
            case 1:
                break;
         
         
            case 2:
               Books books = new Books();
                System.out.println(books.addBook(1, "The Alchemist", "189-hn3-038n", "Paulo Cohelo"));
                System.out.println(books.addBook(2, "Mary Had a Little Lamb", "37h-193a-9jj", "Mozart"));
                System.out.println(books.addBook(3, "Calculus 1", "777-777-7777", "Newton"));

                System.out.println(books.displayBooks());

                // Test the Queue system with the Alchemist.		
                System.out.println("Juan checks out 'The Alchemist'.");
                System.out.println(books.checkoutBook("The Alchemist", "Juan"));
                System.out.println(books.displayBooks());

                System.out.println("Pablo checks out 'The Alchemist'.");
                System.out.println(books.checkoutBook("The Alchemist", "Pablo"));
                System.out.println(books.displayBooks());

                System.out.println("Manda checks out 'The Alchemist'.");
                System.out.println(books.checkoutBook("The Alchemist", "Manda"));
                System.out.println(books.displayBooks());

                System.out.println("Juan returns 'The Alchemist'.");
                System.out.println(books.returnBook("The Alchemist"));
                System.out.println(books.displayBooks());

                System.out.println("Pita checks out 'The Alchemist'.");
                System.out.println(books.checkoutBook("The Alchemist", "Pita"));
                System.out.println(books.displayBooks());
            break;
            case 3:
                break;

            default:
                System.out.println("Invalid Input");
                break;
        }
    }
}