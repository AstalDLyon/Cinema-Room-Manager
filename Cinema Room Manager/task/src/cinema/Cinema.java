package cinema;

import java.util.Scanner;

public class Cinema {
    private static int ticketsSold;
    private static double currentIncome;

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] array = createArray(scanner);
        int op = 999;
        while (op != 0){
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            op = scanner.nextInt();
            switch (op){
                case 1:
                    showArray(array);
                    break;
                case 2:
                    checkSeat(scanner,array);
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d\n", getTicketsSold());
                    System.out.printf("Percentage: %.2f%%\n", getTicketSoldPercentage(array, ticketsSold));
                    System.out.printf("Current income: $%.0f\n", getCurrentIncome());
                    System.out.printf("Total income: $%.0f\n", calculateIncome(array));
                    break;
                default:
                    System.out.println("Not a option");
            }
        }
        scanner.close();


    }
    public static char[][] createArray(Scanner scanner){
        while (true){
            try{
                char[][] array = new char[getRow(scanner)][getSeats(scanner)];
                for (int i = 0; i < array.length; i++){
                    for (int j = 0; j < array[i].length; j++ ){
                        array[i][j] = 'S';
                    }
                    System.out.println();
                }
                return array;
            } catch (IndexOutOfBoundsException e){
                System.out.println("Os valores não podem ser negativos");
            }

        }
    }
    public static void showArray(char[][] array){
                System.out.println("Cinema:");
                if (array.length == 0 || array[0].length == 0) return; // tratando possível erros com array vazio
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
    public static double calculateIncome(char[][] array){
        if (array.length <= 0 || array[array.length - 1].length <= 0)  return 0;
        int row = array.length;
        int seats = array[array.length - 1].length;
        int ticket;
        int totalNumberOfSeats = row * seats;
        double income;
        if (totalNumberOfSeats <= 60){
            ticket = 10;
            income = totalNumberOfSeats * ticket;
        } else {
            int frontRows = row /2;
            int backRows = row - frontRows;

            double frontIncome = frontRows * seats * 10;
            double backIncome = backRows * seats * 8;

            income = frontIncome + backIncome;
        }

        return income;
    }
    public static void checkSeat(Scanner scanner,  char[][] array){
        int totalRows = array.length;
        int totalSeats = array[array.length - 1].length;

        int inputRow = 0;
        int inputSeat = 0;
        int row = 0;
        int seat = 0;

        while (true) {
            System.out.println("Enter a row number:");
            inputRow = getTicketRow(scanner);
            System.out.println("Enter a seat number in that row:");
            inputSeat = getTicketSeat(scanner);

            if (inputRow < 1 || inputRow > totalRows || inputSeat < 1 || inputSeat > totalSeats) {
                System.out.println("Wrong input!");
                System.out.println(); // Pula linha se o teste pedir espaçamento
                continue; // Volta para o topo do while e deixa o usuário digitar de novo
            }

            row = inputRow - 1;
            seat = inputSeat - 1;

            if (array[row][seat] == 'C') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                continue;
            }
            break;
        }
        array[row][seat] = 'B';
        int frontRows = array.length /2;
        int ticket;
        int totalNumberOfSeats = array.length * array[array.length-1].length;
        if (totalNumberOfSeats <= 60){
            ticket = 10;
            System.out.println("Ticket price: $" + ticket);
            ticketsSold++;
            changeIncome(ticketsSold, ticket);
        } else {
            if (inputRow <= frontRows){
                ticket = 10;
            } else {
                ticket = 8;
            }
            System.out.println("Ticket price: $" + ticket);
            ticketsSold++;
            changeIncome(ticketsSold, ticket);

        }
    }
    public static int getTicketsSold(){ // feito
        return ticketsSold;
    }
    public static  double  getTicketSoldPercentage(char[][] array, int ticketsSold){ // feito
        double ticketsSoldPercentage = ((double) ticketsSold / (array.length * array[array.length - 1].length)) * 100;
        return ticketsSoldPercentage;
    }
    public static  double  getCurrentIncome(){
        return currentIncome;
    }
    public static void changeIncome(int ticketsSold, int ticketPrice){
        currentIncome += ticketPrice;
    }
    public static int getRow(Scanner scanner){ //feito
        while (true){
            System.out.println("Enter the number of rows:");
            int row = scanner.nextInt();
            if (row >= 1){
                return row;
            } else {
                System.out.println("At least 1 row.");
            }
        }
    }
    public static int getSeats(Scanner scanner){ //feito
        while (true){
            System.out.println("Enter the number of seats in each row:");
            int seats = scanner.nextInt();
            if (seats >= 1){
                return seats;
            } else {
                System.out.println("At least 1 seat.");
            }
        }
    }
    public static int getTicketRow(Scanner scanner){ // feito
        while (true){
            int ticketRow = scanner.nextInt();
            if (ticketRow >= 0){
                return ticketRow;
            } else {
                System.out.println("Can be smaller than 0.");
            }
        }
    }
    public static int getTicketSeat(Scanner scanner){ // feito
        while (true){
            int ticketSeat = scanner.nextInt();
            if (ticketSeat >= 0){
                return ticketSeat;
            } else {
                System.out.println("Can be smaller than zero.");
            }
        }
    }
}