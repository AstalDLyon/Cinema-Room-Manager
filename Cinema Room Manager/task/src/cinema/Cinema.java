package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] array = createArray(scanner);
        int op = 999;
        while (op != 0){
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");
            op = scanner.nextInt();
            switch (op){
                case 1:
                    showArray(array);
                    break;
                case 2:
                    checkSeat(scanner,array);
                    break;
            }
        }
        scanner.close();


    }
    public static int getRow(Scanner scanner){
        System.out.println("Enter the number of rows:");
        return scanner.nextInt();
    }
    public static int getSeats(Scanner scanner){
        System.out.println("Enter the number of seats in each row:");
        return scanner.nextInt();
    }
    public static int getTicketRow(Scanner scanner){
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }
    public static int getTicketSeat(Scanner scanner){
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }
    public static char[][] createArray(Scanner scanner){
        char[][] array = new char[getRow(scanner)][getSeats(scanner)];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++ ){
                array[i][j] = 'S';
            }
            System.out.println();
        }
        return array;
    }
    public static void showArray(char[][] array){
        System.out.println("Cinema:");
        if (array.length == 0 || array[0].length == 0) return; // tratando possivel erros com array vazio
        System.out.print("  ");
        for (int j = 0; j < array[0].length; j++) {
            System.out.printf("%d ", j + 1);
        }
        System.out.println();
        for (int i = 0; i < array.length; i++){
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < array[i].length; j++ ){
                System.out.printf("%s ",array[i][j]);
            }
            System.out.println();
        }
    }
    public static int calculateIncome(Scanner scanner){
        int row = getRow(scanner);
        int seats = getSeats(scanner);
        int ticket = 0;
        int totalNumberOfSeats = row * seats;
        int income = 0;
        if (row <= 0 || seats <= 0)  return 0;
        if (totalNumberOfSeats <= 60){
            ticket = 10;
            income = totalNumberOfSeats * ticket;
        } else {
            int frontRows = row /2;
            int backRows = row - frontRows;

            int frontIncome = frontRows * seats * 10;
            int backIncome = backRows * seats * 8;

            income = frontIncome + backIncome;
        }

        return income;
    }
    public static void checkSeat(Scanner scanner,  char[][] array){
        int inputRow = getTicketRow(scanner);
        int inputSeat = getTicketSeat(scanner);
        int row = inputRow - 1;
        int seat = inputSeat - 1;
        array[row][seat] = 'B';
        int frontRows = array.length /2;
        int ticket = 0;
        int totalNumberOfSeats = array.length * array[array.length-1].length;
        if (totalNumberOfSeats <= 60){
            ticket = 10;
            System.out.println("Ticket price: $" + ticket);
        } else {
            if (inputRow <= frontRows){
                ticket = 10;
                System.out.println("Ticket price: $" + ticket);
            } else {
                ticket = 8;
                System.out.println("Ticket price: $" + ticket);
            }

        }
    }
}