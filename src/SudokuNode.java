import java.util.ArrayList;
import java.util.HashSet;

public class SudokuNode {
    private SudokuState state;
    private ArrayList<HashSet<Short>> goodValues;

    public SudokuNode(int n) {
        this.state = new SudokuState(n);
        this.goodValues = new ArrayList<>();
        for (int i = 0; i < n*n; i++){
            this.goodValues.add(new HashSet<Short>());
        }
    }

    public SudokuNode(SudokuState state){
        this.state = state;
        int n = this.state.getBoard().length;
        this.goodValues = new ArrayList<>();
        for (int i = 0; i < n*n; i++){
            this.goodValues.add(new HashSet<Short>());
        }
    }

    public SudokuNode(SudokuState state, ArrayList<HashSet<Short>> goodValues) {
        this.state = new SudokuState(state.getBoard());
        this.goodValues = new ArrayList<>();
        for(HashSet<Short> values : goodValues){
            this.goodValues.add(new HashSet<Short>(values));
        }
    }

    public SudokuNode copy(){
        return new SudokuNode(this.state, this.goodValues);
    }
    
    public HashSet<Short> getGoodValues(int row, int column) {
        int n = this.state.getBoard().length;
        return this.goodValues.get(row*n+column);
    }

    public void assign(int row, int col, short value){
        this.state.assign(row, col, value);
        this.getGoodValues(row, col).clear();
        this.getGoodValues(row, col).add(value);
    }

    public SudokuState getState() { return this.state; }
    public ArrayList<HashSet<Short>> getAllGoodValues() { return this.goodValues; }
}