package view;

import controllers.EscapeRoomController;
import exceptions.NoAvailableCluesException;
import exceptions.NoAvailableDecosException;

import java.util.Scanner;

public class Menu {

    public static void startMenu() throws Exception {
        final EscapeRoomController controller = new EscapeRoomController();
        controller.createEscapeRoom();
        boolean exit = false;
        do {
            try {
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

                        break;
                    case 13:

                        break;
                    case 14:
                        //method3();
                        break;
                    case 0:
                        System.out.println("Finishing the program...");
                        exit = true;
                        break;
                }
           } catch (NoAvailableCluesException | NoAvailableDecosException e) {
                System.out.println(e.getMessage());
                System.out.println("Returning to the main menu...");
            }
        } while (!exit);
    }

    public static byte menu() {
        Scanner entrada = new Scanner(System.in);
        byte option;
        final byte MINIMO = 0;
        final byte MAXIMO = 13;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Create room");
            System.out.println("2. Create clue"); // do you want to add clue to the room?
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

            option = entrada.nextByte();
            if (option < MINIMO || option > MAXIMO) {
                System.out.println("Choose valid option");
            }
        } while (option < MINIMO || option > MAXIMO);
        return option;
    }
}


//create clue and add clue to the room (muestra todos las pistas (el nombre e id) y el user selecciona id de la pista)
//add clue to the room
//create decoration and add it to the room (lo mismo)
//add the decoration to the room
//create player
//add player to the room (player is playing in the room) (muestra todos los players, escoge el id, y muestra todos los rooms disponibles, escoges el id de room)
//        show rooms, clues, decos  (muestra el inventario de todo, además de las cantidades -> cuántas pistas y decos, rooms hay)
//        remove room, clue, deco (elimina room, clue y deco se desvinculan y no se borran. Primero debería de mostrar todo lo que hay disponible y luego borrarlo)
//generate tickets (que muestren jugadores, seleccionas, y luego muestras rooms disponibles y seleccionas)
//calculate total tickets
//notify subscribed members (observer o callback)
//generate certificates(muestre todos los jugadores, y seleccionas al jugador, luego miras los rooms en las que ha jugado y seleccionas room), (en el certificado saldrán las pistas que ha usado el jugador, la sala con su dificultad y temática)