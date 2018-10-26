import java.util.Arrays;

public class SudokuState {
    private short[][] board;

    public SudokuState(int n){
        this.board = new short[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                this.board[i][j] = 0;
            }
        }
    }

    public SudokuState(short[][] board) {
        int n = board.length;
        this.board = new short[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    public SudokuState(String board) {
        int n = (int)Math.sqrt(board.length());
        this.board =new short[n][n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                this.board[i][j] = (short)Character.getNumericValue(board.charAt(k++));
            }
        }
    }

    public boolean isFilled() {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (this.board[i][j] == 0 ){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolved() {
        int n = board.length;
        int m = (int) Math.sqrt(n);
        for (int i = 0; i < n; i++) {
            short[] column = new short[n];
            short[] square = new short[n];
            short[] row = this.board[i].clone();
    
            for (int j = 0; j < n; j ++) {
                column[j] = this.board[j][i];
                square[j] = this.board[(i / m) * m + j / m][i * m % n + j % m];
            }
            if (!(validate(column) && validate(row) && validate(square)))
                return false;
        }
        return true;
    }
    
    private boolean validate(short[] check) {
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

    public void assign(int row, int col, short value){
        if (this.board[row][col] != 0) {
            throw new UnsupportedOperationException(
                "Row: " + row + " Col: " + col + " has already been assigned!");
        }
        this.board[row][col] = value;
    }

    public void unassign(int row, int col) {
        if (this.board[row][col] == 0) {
            throw new UnsupportedOperationException(
                "Row: " + row + " Col: " + col + " has not yet been assigned!");
        }
        this.board[row][col] = 0;
    }
    
    public short[][] getBoard(){ return this.board; }

    public String toString() {
        int n = board.length;
        int m = (int)Math.sqrt(n);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.append(' ');
                if (this.board[i][j] == 0) {
                    out.append('.');
                } else{
                    out.append(this.board[i][j]);
                }
                if (j > 0 && j < n-1 && (j+1) % m == 0) {
                    out.append(" |");
                }
            }
            out.append('\n');
            if (i > 0 && i < n-1 && (i+1) % m == 0){
                for (int j = 0; j < 2 * (n + m - 1); j++) {
                    out.append('-');
                }
                out.append('\n');
            }
        }
        return out.substring(0, out.length() - 1);
    }

    public static void main(String[] args) {
        String testBoard = "000020040008035000000070602031046970200000000000501203049000730000000010800004000";
        SudokuState state = new SudokuState(testBoard);
        System.out.println(state);              
    }

}