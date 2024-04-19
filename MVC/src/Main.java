public class Main {
    public static void main(String[] args) {
        View window = new View();
        window.setVisible(true);
        Controller controller = new Controller(window);
        controller.execute();
    }
}
