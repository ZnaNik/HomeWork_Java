package part2.homework1;

public interface iSportsMan {

    int getJumpLimit();
    int getRunLimit();
    void setRunLimit(int limit);
    void setJumpLimit(int limit);

    void jump();
    void run();
}
