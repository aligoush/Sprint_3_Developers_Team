package view;

import controllers.EscapeRoomController;

import java.util.Scanner;

public class Menu {

    public static void startMenu() {
        final EscapeRoomController controller = new EscapeRoomController();
        controller.createEscapeRoom();
        boolean exit = false;
        do {
            switch (menu()) {
                case 1:
                    controller.createRoom();
                    break;
                case 2:
                    controller.createClue();
                    break;
                case 3:
                    controller.addClueToRoom();
                    break;
                case 4:
                    controller.createDecoration();
                    break;
                case 5:
                    controller.addDecoToRoom();
                    break;
                case 6:
                    controller.createPlayer();
                    break;
                case 7:
                    controller.showAllPlayers();
                    break;
                case 8:
                    controller.delete();
                    break;
                case 9:
                    controller.showInventory();
                    break;
                case 10:
                    controller.addPlayerAndCreateTicket();
                    break;
                case 11:
                    controller.getTotalTicketsPrice();
                    break;
                case 12:
                    controller.notifySubscribers();
                    break;
                case 13:
                    controller.createCertificate();
                    break;
                case 14:
                    controller.assignCertificate();
                case 0:
                    System.out.println("Finishing the program...");
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static byte menu() {
        Scanner entrada = new Scanner(System.in);
        byte option;
        final byte MINIMO = 0;
        final byte MAXIMO = 14;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Create room");
            System.out.println("2. Create clue");
            System.out.println("3. Add clue to the room");
            System.out.println("4. Create decoration");
            System.out.println("5. Add decoration to the room");
            System.out.println("6. Create player");
            System.out.println("7. Show players");
            System.out.println("8. Remove room or items");
            System.out.println("9. Show inventory");
            System.out.println("10. Add player to the game and generate ticket");
            System.out.println("11. Calculate total value of tickets");
            System.out.println("12. Notify subscribed members");
            System.out.println("13. Generate certificates of player");
            System.out.println("14. Asign certificate to a player");

            option = entrada.nextByte();
            if (option < MINIMO || option > MAXIMO) {
                System.out.println("Choose valid option");
            }
        } while (option < MINIMO || option > MAXIMO);
        return option;
    }
}


//notify subscribed members (observer o callback)
//generate certificates(muestre todos los jugadores, y seleccionas al jugador, luego miras los rooms en las que ha jugado y seleccionas room), (en el certificado saldrán las pistas que ha usado el jugador, la sala con su dificultad y temática)