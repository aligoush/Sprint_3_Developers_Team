package view;

import java.util.Scanner;

public class Menu {

        public void startMenu() {

            boolean exit = false;

            do{
                switch(menu()){
                    case 1: createRoom();
                        break;
                    case 2: method2();
                        break;
                    case 3: method3();
                        break;
                    case 0: System.out.println("Gracias por usar la app");
                        exit = true;
                        break;
                }
            }while(!exit);
        }

        public static byte menu(){
            Scanner entrada = new Scanner(System.in);
            byte option;
            final byte MINIMO = 0;
            final byte MAXIMO = 3;

            do{
                System.out.println("\nMENú PRINCIPAL");
                System.out.println("1. Crear sala");
                System.out.println("2. Crear pista, Y ¿quiere añadir la pista a la sala?");
                System.out.println("3. Añadir pista a sala");
                System.out.println("4. Crear decoración, Y ¿quiere añadir la decoración a la sala?");
                System.out.println("5. Añadir decoración a la sala");
                System.out.println("6. Mostrar inventario ");
                System.out.println("7. ");
                option = entrada.nextByte();
                if(option < MINIMO || option > MAXIMO){
                    System.out.println("Escoja una opción válida");
                }
            }while(option < MINIMO || option > MAXIMO);
            return option;
        }

        public static void createRoom(){
            /*RoomService holi = new RoomService();

            holi.createRoom();
*/

        }

        public static void method2(){
            System.out.println("Method 2");
        }

        public static void method3(){
            System.out.println("Method 3");
        }


    }

