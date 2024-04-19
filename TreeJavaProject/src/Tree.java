import java.util.Comparator;

public class Tree<T> {
    Node root;
    private Comparator<T> comparator;

    class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
            left = right = null;
        }
    }
    Tree() {
        this.root = null;
        this.comparator = null;
    }

    Tree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public boolean search(T value) {
        return search(root, value);
    }

    private boolean search(Node node, T value) {
        if (node == null) {
            return false;
        }
        if (comparator.compare(value, node.value) == 0) {
            return true;
        } else if (comparator.compare(value, node.value) < 0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }
    public void insert(T key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("You can't pass a node with a null value to the tree.");
        }
        root = insert(root, key);
    }
    private Node insert(Node node, T key) throws IllegalArgumentException {
        if (node == null) {
            Node appended = new Node(key);
            node = appended;
            return node;
        }  else {
            if (comparator.compare(key, node.value) == 0) {
                throw new IllegalArgumentException("You can't pass two identical parameters to a tree.");
            } else if (comparator.compare(key, node.value) < 0) {
                node.left = insert(node.left, key);
            } else {
                node.right = insert(node.right, key);
            }
        }
        return node;
    }

    public void delete(T key) {
        root = delete(root, key);
    }

    private Node delete(Node node, T key) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(key, node.value) < 0) {
            node.left = delete(node.left, key);
        } else if (comparator.compare(key, node.value) > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node oneLeftAlwaysRight = node.left;
                while (oneLeftAlwaysRight.right != null) {
                    oneLeftAlwaysRight = oneLeftAlwaysRight.right;
                }
                T temp = oneLeftAlwaysRight.value;
                delete(root, oneLeftAlwaysRight.value);
                node.value = temp;
            }
        }
        return node;
    }
    public void lnr() {
        System.out.print("LNR: \n");
        lnr(root);
        System.out.println();
    }
    private void lnr(Node node) {
        if (node != null) {
            lnr(node.left);
            System.out.print(node.value + " ");
            lnr(node.right);
        }
    }
    public void nlr() {
        System.out.print("NLR: \n");
        nlr(root);
        System.out.println();
    }
    private void nlr(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            nlr(node.left);
            nlr(node.right);
        }
    }
    public void lrn() {
        System.out.print("LRN: \n");
        lrn(root);
        System.out.println();
    }
    private void lrn(Node node) {
        if (node != null) {
            lrn(node.left);
            lrn(node.right);
            System.out.print(node.value + " ");
        }
    }
}
