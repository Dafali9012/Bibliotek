import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    Program() {
        settingsPath =  Paths.get("src/books.txt");
        booksList = Books.getBooks(settingsPath);
        PrintMenu();
    }

    Scanner scan = new Scanner(System.in);
    private final Path settingsPath;
    private List<String> booksList;

    private void PrintMenu() {
        System.out.println("Bibliotek");
        System.out.println("-----------------------");
        System.out.println();
        System.out.println("1. Lista tillgängliga böcker");
        System.out.println("2. Lista utlånade böcker");
        System.out.println("3. Sök efter bok");
        System.out.println();
        System.out.print("Inmatning: ");
        int selection = scan.nextInt();
        scan.nextLine();

        switch (selection) {
            case 1:
                System.out.println();
                PrintList("0");
                break;
            case 2:
                System.out.println();
                PrintList("1");
                break;
            case 3:
                PrintSearch();
                break;


        }
    }

    private void PrintList(String status) {
        System.out.println("Bibliotek");
        System.out.println("-----------------------");
        System.out.println();
        ArrayList<String> tempBook = new ArrayList<String>();
        int i = 0;
        for(String book:booksList) {
            String[] bookArr = book.split("_");

            if(!bookArr[1].contains(status)) {
                System.out.println(++i+". "+bookArr[0]+", status: "+bookArr[1]);
                tempBook.add(book);
            }
        }

        System.out.println();
        System.out.print("välj bok: ");
        int input = scan.nextInt();
        scan.nextLine();



        String[] bookArr = tempBook.get(input-1).split("_");
        if(status == "1") tempBook.set(input-1, bookArr[0]+"_1");
        if(status == "0") tempBook.set(input-1, bookArr[0]+"_0");

        for(int j = 0; j < booksList.size(); j++ ){
            if (booksList.get(j).contains(bookArr[0])){
                booksList.set(j, tempBook.get(input-1));
            }
        }

        bookArr = tempBook.get(input-1).split("_");

        if(bookArr[1].equals("0"))System.out.println(bookArr[0]+" Är nu utlånad");
        if(bookArr[1].equals("1"))System.out.println(bookArr[0]+" Är nu tillbakalämnad");

        try {
            Files.write(settingsPath, booksList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void PrintSearch() {
        System.out.println("Bibliotek");
        System.out.println("-----------------------");
        System.out.println();
        System.out.print("Mata in sökord: ");
        String input = scan.nextLine();
        System.out.println();
        PrintSearchOutput(input);
    }

    private void PrintSearchOutput(String input) {
        System.out.println("Bibliotek");
        System.out.println("-----------------------");
        System.out.println();
        for(String book:booksList) {
            String[] bookArr = book.split("_");
            if(book.contains(input)) {
                System.out.println(bookArr[0]);
            }
        }
    }
}
