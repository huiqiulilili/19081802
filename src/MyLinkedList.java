public class MyLinkedList implements List{

    private static class Node{
        private int val;
        private Node next = null;
        private Node prev = null;

        public Node(int val){
            this.val = val;
        }

        public Node(int val,Node prev,Node next){
            this(val);
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return String.format("Node(%d)",val);
        }

    }

    Node head = null;
    Node last = null;
    private int size = 0;

    @Override
    public boolean add(int element) {
        return add(0,element);
    }

    @Override
    public boolean add(int index, int element) {
        if(index < 0 || index > size){
            System.out.println("下表不合法");
            return false;
        }

        if(index == 0){
            head = new Node(element,null,head);
            if(head.next != null) {
                head.next.prev = head;
            }else{
                last = head;
            }
            size++;
            return true;
        }

        if(index == size){
            last = new Node(element,last,null);
            last.prev.next = last;
            if(size == 0){
                head = last;
            }
            size++;
            return true;
        }
        //找到index下表的结点

        Node node = getNode(index);

        Node newNode = new Node(element,node.prev,node);
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
        return true;
    }

    private Node getNode(int index){
        if(index < size / 2){
            Node cur = head;
            for(int i = 0;i < index;i ++){
                cur = cur.next;
            }
            return cur;
        }else{
            Node cur = last;
            for(int i = 0;i < size - index - 1;i ++){
                cur = cur.prev;
            }
            return cur;
        }
    }

    @Override
    public int get(int index) {
        if(index < 0 || index > size){
            System.out.println("下表不合法");
            return -1;
        }
        return  getNode(index).val;
    }

    @Override
    public int set(int index, int val) {

        if(index < 0 || index > size){
            System.out.println("下表不合法");
            return -1;
        }
        Node old = getNode(index);
        int oldNum = old.val;
        old.val = val;
        return oldNum;
    }

    @Override
    public int remove(int index) {
        if(index < 0 || index > size){
            System.out.println("下表不合法");
            return -1;
        }
        Node node = getNode(index);

        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }else{
            last = node.prev;
        }
        size--;
        return node.val;
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
        for (Node c = head; c != null; c = c.next) {
            r += (c.val + ",");
        }
        r += "]";

        return r;
    }
}
