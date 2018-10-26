public class Arc {
    SudokuVar head;
    SudokuVar tail;
    public Arc(SudokuVar head, SudokuVar tail){
        this.head = head;
        this.tail = tail;
    }
    public SudokuVar getHead() {return head;}
    public SudokuVar getTail() {return tail;}
}