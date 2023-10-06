public class Main {

    public static void main(String[] args) {
        CircularDoublyLinkedList <Character> list = new CircularDoublyLinkedList<>();
        list.addFirst('b');
        list.addLast('k');
        list.addLast('m');
        list.addLast('n');
        System.out.println(list);
        System.out.println(list.indexPalindrome());
        list.removeFirst();
        list.removeLast();
        System.out.println(list);
        System.out.println(list.indexPalindrome());
    }
}
