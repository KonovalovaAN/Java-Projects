import java.util.Comparator;
public class Main {
    public static void main(String[] args) {
        Comparator<Hero> heroComparator = Comparator.comparing(Hero::getScore);
        Tree<Hero> tree1 = new Tree<>(heroComparator);

        tree1.insert(new Hero(9, "Max"));
        tree1.insert(new Hero(4, "Jack"));
        tree1.insert(new Hero(3, "Spider"));
        tree1.insert(new Hero(6, "Laf"));
        tree1.insert(new Hero(5, "Gop"));
        tree1.insert(new Hero(7, "RGB"));
        tree1.insert(new Hero(17, "Mr Bob"));
        tree1.insert(new Hero(22, "Shon"));
        tree1.insert(new Hero(20, "Glag"));

        System.out.println(tree1.search(new Hero(4, "Jack")));
        System.out.println(tree1.search(new Hero(45, "")));

        tree1.nlr();
        tree1.lnr();
        tree1.lrn();

        tree1.delete(new Hero(6, "Laf"));

        tree1.nlr();
        tree1.lnr();
        tree1.lrn();

        Comparator<Integer> integerComparator = Integer::compareTo;

        Tree<Integer> tree = new Tree<>(integerComparator);

        tree.insert(9);
        tree.insert(4);
        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(7);
        tree.insert(17);
        tree.insert(22);
        tree.insert(20);

        tree.nlr();
        tree.lnr();
        tree.lrn();
    }
}
