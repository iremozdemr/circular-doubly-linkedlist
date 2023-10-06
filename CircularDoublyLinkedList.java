public class CircularDoublyLinkedList <E> {
        private static class Node<E> {

            /** The element stored at this node */
            private E element;               // reference to the element stored at this node

            /** A reference to the preceding node in the list */
            private Node<E> prev;            // reference to the previous node in the list

            /** A reference to the subsequent node in the list */
            private Node<E> next;            // reference to the subsequent node in the list

            /**
             * Creates a node with the given element and next node.
             *
             * @param e  the element to be stored
             * @param p  reference to a node that should precede the new node
             * @param n  reference to a node that should follow the new node
             */
            public Node(E e, Node<E> p, Node<E> n) {
                element = e;
                prev = p;
                next = n;
            }

            public E getElement() {
                return element;
            }

            /**
             * Returns the node that precedes this one (or null if no such node).
             * @return the preceding node
             */
            public Node<E> getPrev() { return prev; }

            /**
             * Returns the node that follows this one (or null if no such node).
             * @return the following node
             */
            public Node<E> getNext() { return next; }

            // Update methods
            /**
             * Sets the node's previous reference to point to Node n.
             * @param p    the node that should precede this one
             */
            public void setPrev(Node<E> p) { prev = p; }

            /**
             * Sets the node's next reference to point to Node n.
             * @param n    the node that should follow this one
             */
            public void setNext(Node<E> n) { next = n; }
        } //----------- end of nested Node class -----------

        // instance variables of the DoublyLinkedList
        /** Sentinel node at the beginning of the list */
        private Node<E> header;                    // header sentinel

        /** Number of elements in the list (not including sentinels) */
        private int size = 0;                      // number of elements in the list

        /** Constructs a new empty list. */
        public CircularDoublyLinkedList() {
            header = new Node<>(null, null, null);      // create header
        }

        // public accessor methods
        /**
         * Returns the number of elements in the linked list.
         * @return number of elements in the linked list
         */
        public int size() { return size; }

        /**
         * Tests whether the linked list is empty.
         * @return true if the linked list is empty, false otherwise
         */
        public boolean isEmpty() { return size == 0; }

        /**
         * Returns (but does not remove) the first element of the list.
         * @return element at the front of the list (or null if empty)
         */
        public E first() {
            if (isEmpty()) return null;
            return header.getNext().getElement();   // first element is beyond header
        }

        /**
         * Returns (but does not remove) the last element of the list.
         * @return element at the end of the list (or null if empty)
         */
        public E last() {
            if (isEmpty()) return null;
            return header.getPrev().getElement();    // last element is before trailer
        }


        // private update methods
        /**
         * Adds an element to the linked list in between the given nodes.
         * The given predecessor and successor should be neighboring each
         * other prior to the call.
         *
         * @param predecessor   node just before the location where the new element is inserted
         * @param successor     node just after the location where the new element is inserted
         */
        private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
            // create and link a new node
            Node<E> newest = new Node<>(e, predecessor, successor);
            predecessor.setNext(newest);
            successor.setPrev(newest);
            size++;
        }

        /**
         * Removes the given node from the list and returns its element.
         * @param node    the node to be removed (must not be a sentinel)
         */
        private E remove(Node<E> node) {
            Node<E> predecessor = node.getPrev();
            Node<E> successor = node.getNext();
            predecessor.setNext(successor);
            successor.setPrev(predecessor);
            size--;
            return node.getElement();
        }

        /*
         * Implements your methods here
         */

        public void addFirst(E e) {
            if(header.getPrev()==null && header.getNext()==null){
                Node<E> newFirst = new Node<>(e,header,header);
                header.setPrev(newFirst);
                header.setNext(newFirst);
            }
            else{
                Node<E> secondNode = header.next;
                Node<E> newFirst = new Node<>(e,header,secondNode);
                header.setNext(newFirst);
                secondNode.setPrev(newFirst);
            }
            size++;
        }

        public void addLast(E e) {
            if(header.getPrev()==null && header.getNext()==null){
                Node<E> newLast = new Node<>(e,header,header);
                header.setPrev(newLast);
                header.setNext(newLast);
            }
            else{
                Node<E> oldLast = header.prev;
                Node<E> newLast = new Node<>(e,oldLast,header);
                oldLast.setNext(newLast);
                header.setPrev(newLast);
            }
            size++;
        }


        public E removeFirst() {
            if(isEmpty()){
                return null;
            }
            else if(size == 1){
                E removedE = header.getNext().element;
                Node<E> removedN = header.getNext();
                header.setPrev(null);
                header.setNext(null);
                removedN.setPrev(null);
                removedN.setNext(null);
                size--;
                return removedE;
            }
            else{
                E removedE = header.getNext().element;
                Node<E> removedN = header.getNext();
                header.setNext(removedN.getNext());
                removedN.getNext().setPrev(header);
                removedN.setPrev(null);
                removedN.setNext(null);
                size--;
                return removedE;
            }
        }


        public E removeLast() {
            if(isEmpty()){
                return null;
            }
            else if(size == 1){
                E removedE = header.getNext().element;
                Node<E> removedN = header.getNext();
                header.setPrev(null);
                header.setNext(null);
                removedN.setPrev(null);
                removedN.setNext(null);
                size--;
                return removedE;
            }
            else{
                E removedE = header.getPrev().element;
                Node<E> removedN = header.getPrev();
                header.setPrev(removedN.getPrev());
                removedN.getPrev().setNext(header);
                removedN.setPrev(null);
                removedN.setNext(null);
                size--;
                return removedE;
            }
        }


        public String toString() {
            if(size == 0){
                return null;
            }
            else{
                String string = "";
                Node<E> newHeader = header;
                for(int i = 0; i<size; i++){
                    string = string + " " + String.valueOf(newHeader.next.getElement());
                    newHeader = newHeader.next;
                }
                return string.substring(1);
            }
        }

        public int indexPalindrome(){
            if(size == 0){
                return -1;
            }
            else{
                Object[] array = new Object[size];
                Node<E> newHeader = header;
                for(int i = 0; i<size; i++){
                    array[i] = newHeader.getNext().getElement();
                    //System.out.println(array[i]);
                    newHeader = newHeader.next;
                }
                int palindrome = 0;
                int index = 0;
                for(int start = 0; start<array.length; start++){
                    String word = "";
                    //String word = String.valueOf(array[start]);
                    for(int add = start; add<array.length;add++){
                        word = word + String.valueOf(array[add]);
                    }
                    for(int add = 0; add<start+1;add++){
                        word = word + String.valueOf(array[add]);
                    }
                    //System.out.println("word:" + word);
                    word = word.toLowerCase();
                    int left = 0;
                    int right = word.length() - 1;
                    boolean isPalindrome = true;
                    while (left < right) {
                        if (word.charAt(left) != word.charAt(right)) {
                            isPalindrome = false;
                            palindrome++;
                        }
                        left++;
                        right--;
                    }
                    if(isPalindrome == true){
                        if(index <= start+1){
                            index = start+1;
                        }
                    }
                    if(palindrome == array.length){
                        return -1;
                    }
                }
                return index;
            }
        }
} 
