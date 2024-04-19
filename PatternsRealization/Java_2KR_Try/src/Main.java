public class Main {
    public static void main(String[] args) {
        View view = new View("KR2");
        Controller controller = new Controller(view);
        controller.execute();
    }
}