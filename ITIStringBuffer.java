public class ITIStringBuffer {

    SinglyLinkedList<String> buffer;

    public ITIStringBuffer() {
        this.buffer = new SinglyLinkedList();

    }
 
    public ITIStringBuffer(String firstString) {
        this.buffer = new SinglyLinkedList<>();
        this.buffer.add(firstString);
    }

    public void append(String nextString){
        this.buffer.add(nextString);
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for(String str : buffer){
            res.append(str);
        }
        return res.toString();
    }

}
