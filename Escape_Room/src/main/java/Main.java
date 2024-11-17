import view.Menu;

public class Main {

    public static void main(String[] args) {

        try {
            Menu.startMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
