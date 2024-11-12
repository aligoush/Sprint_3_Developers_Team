package view;

import java.util.Scanner;

public class Menu {

    public void startMenu() {

        boolean exit = false;

        do {
            switch (menu()) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    method2();
                    break;
                case 3:
                    method3();
                    break;
                case 0:
                    System.out.println("Gracias por usar la app");
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static byte menu() {
        Scanner entrada = new Scanner(System.in);
        byte option;
        final byte MINIMO = 0;
        final byte MAXIMO = 3;

        do {
            System.out.println("\nMENú PRINCIPAL");
            System.out.println("1. Create room");
            System.out.println("2. Create clue"); // do you want to add clue to the room?
            System.out.println("3. Add clue to the room");
            System.out.println("4. Crear decoración, Y ¿quiere añadir la decoración a la sala?");
            System.out.println("5. Añadir decoración a la sala");
            System.out.println("6. Mostrar inventario ");
            System.out.println("7. ");
            option = entrada.nextByte();
            if (option < MINIMO || option > MAXIMO) {
                System.out.println("Escoja una opción válida");
            }
        } while (option < MINIMO || option > MAXIMO);
        return option;
    }

    public static void createRoom() {
            /*RoomService holi = new RoomService();

            holi.createRoom();
*/

    }

    public static void method2() {
        System.out.println("Method 2");
    }

    public static void method3() {
        System.out.println("Method 3");
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