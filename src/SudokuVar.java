public class SudokuVar{
    private int row;
    private int col;
    public SudokuVar(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int R(){return this.row;}
    public int C(){return this.col;}

    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!SudokuVar.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final SudokuVar other = (SudokuVar) obj;
        return this.row == other.row && this.col == other.col;
    }
}