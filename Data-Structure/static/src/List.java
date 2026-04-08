public class List {
    private int nElem;
    private int[] data;

    public List(int size){
        this.data = new int[size];
        this.nElem = 0;
    }

    public boolean search(int value){
        int i = 0;

        while(i < this.nElem){
            if (this.data[i] == value) return true;
            i++;
        }
        return false;
    }

    public boolean insert(int value){
        if (this.nElem == this.data.length) return false;
        if (search(value)) return false;

        this.data[nElem] = value;
        this.nElem++;
        return true;
    }

    public boolean remove(int value){
        if (!search(value)) return false;
        int i = 0;

        while(i < this.nElem){
            if (this.data[i] == value){
                this.nElem--;
                this.data[i] = this.data[this.nElem];
                return true;
            }
            i++;
        }
        return false;
    }

    public void print(){
        int i = 0;
        System.out.print("[");
        while (i < this.nElem){
            System.out.print(this.data[i] + " ,");
            i++;
        }
        System.out.print("]");
    }
}
