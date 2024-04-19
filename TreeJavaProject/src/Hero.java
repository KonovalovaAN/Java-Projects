public class Hero {
    String name;
    int score;

    public Hero(int score, String name) {
        this.name = name;
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public String toString() {
        return "name: " + name + "; score: " + score + '\n';
    }
}
