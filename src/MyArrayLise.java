import java.util.Arrays;

public class MyArrayLise  implements List{
    private int[] array = null;
    private int size = 0;



    @Override
    public boolean add(int element) {
        add(0,element);
        return true;
    }

    @Override
    public boolean add(int index, int element) {
        if(index < 0 || index > size){
            System.out.println("下表错误");
            return false;
        }

        ensureCapacity();

        System.arraycopy(array,index ,array,index + 1,size - index);

        array[index] = element;

        size++;
        return false;
    }

    private void ensureCapacity() {
        if(array != null && size < array.length){
            return;
        }

        int capacity;

        if(array == null){
            capacity = 10;
        }else {
            capacity = 2 * array.length;
        }

        if(array != null){
            array = Arrays.copyOf(array,capacity);
        }else{
            array = new int[capacity];
        }
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("下标错误");
            return -1;
        }
        return array[index];
    }

    @Override
    public int set(int index, int val) {
        if (index < 0 || index >= size) {
            System.out.println("下标错误");
            return -1;
        }
        int oldNum = array[index];
        array[index] = val;
        return oldNum;
    }

    @Override
    public int remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("下标错误");
            return -1;
        }
        int oldNum = array[index];
        System.arraycopy(array,index + 1,array,index,size - index - 1);
        size--;
        return oldNum ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String r = "[";
        for (int i = 0;i < size();i ++) {
            r += (array[i] + ",");
        }
        r += "]";

        return r;
    }
}
