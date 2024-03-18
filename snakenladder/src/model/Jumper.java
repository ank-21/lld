package model;

public class Jumper {
    private final int start;
    private final int end;

    public Jumper(int start, int end){
        this.start  = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
