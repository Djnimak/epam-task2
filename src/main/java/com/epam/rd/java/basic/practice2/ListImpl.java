package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    ListImpl.Node first;
    ListImpl.Node last;
    int listSize;

    public ListImpl() {
        this(0);
    }

    public ListImpl(int listSize) {
        this.listSize = listSize;
        first = null;
        last = null;
    }

    static class Node {
        Object data;
        ListImpl.Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void clear() {
        if (getFirst() != null) {
            this.first = null;
        }
        if (getLast() != null) {
            this.last = null;
        }
        if (listSize != 0) {
            this.listSize = 0;
        }
    }

    @Override
    public int size() {
        return this.listSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        ListImpl.Node current;
        ListImpl.Node lastReturned;
        ListImpl.Node next = last;
        int nextIndex = 0;

        public IteratorImpl() {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < listSize;
        }

        @Override
        public Object next() {
            Object data;
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                data = current.data;
                current = current.next;
                nextIndex++;
                return data;
            }
        }

        @Override
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else {
                ListImpl.Node lastNext = this.lastReturned.next;
                ListImpl.this.remove(this.lastReturned);
                if (this.next == this.lastReturned) {
                    this.next = lastNext;
                } else {
                    --this.nextIndex;
                }
                this.lastReturned = null;
            }
        }
    }

    @Override
    public void addFirst(Object element) {
        ListImpl.Node newNode = new ListImpl.Node(element);
        if (this.first != null) {
            newNode.next = first;
        }
        first = newNode;
        ++this.listSize;
    }

    @Override
    public void addLast(Object element) {
        ListImpl.Node newNode = new ListImpl.Node(element);
        if (first == null) {
            first = newNode;
        } else {
            ListImpl.Node cur = this.first;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
        last = newNode;
        ++this.listSize;
    }

    @Override
    public void removeFirst() {
        if (this.first != null) {
            this.first = this.first.next;
            --this.listSize;
        }
    }

    @Override
    public void removeLast() {
        ListImpl.Node cur = this.first;
        ListImpl.Node prev = this.first;
        if (this.first != null) {
            if (this.first.next == null) {
                this.first = null;
            } else {
                while (cur.next != null) {
                    prev = cur;
                    cur = cur.next;
                }
                prev.next = null;
                last = prev;
            }
            --this.listSize;
        }
    }

    @Override
    public Object getFirst() {
        ListImpl.Node f = this.first;
        if (f != null) {
            return f.data;
        } else {
            return null;
        }
    }

    @Override
    public Object getLast() {
        ListImpl.Node l = this.last;
        if (l != null) {
            return l.data;
        } else {
            return null;
        }
    }

    @Override
    public Object search(Object element) {
        ListImpl.Node x;
        Object e = null;
        for (x = this.first; x != null; x = x.next) {
            if (element == x.data) {
                e = x.data;
            }
        }
        return e;
    }

    @Override
    public boolean remove(Object element) {
        ListImpl.Node cur = this.first;
        ListImpl.Node prev = this.first;
        while (cur != last && cur.data != element) {
            prev = cur;
            cur = cur.next;
        }
        if (element == null) {
            prev.next = cur.next;
            if (cur == last) {
                last = prev;
            }
            --this.listSize;
            return true;
        }
        if (search(element) != null) {
            if (this.first.data == element) {
                this.first = this.first.next;
                --this.listSize;
                return true;
            }
            if (cur != null && cur.data.equals(element)) {
                prev.next = cur.next;
                --this.listSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (listSize == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder("[");
        Node current = first;
        while (current.next != null) {
            s.append(current.data);
            s.append(", ");
            current = current.next;
        }
        s.append(current.data);
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        System.out.println(list.remove('D'));
        list.addFirst('A');
        list.addLast('B');
        list.addLast(null);
        list.addLast('C');
        System.out.println(list);
        System.out.println(list.remove(null));
        System.out.println(list);
        System.out.println(list.search('D'));
        System.out.println(list.remove('C'));
        System.out.println(list.search('C'));
        System.out.println(list.remove('C'));
        System.out.println(list.remove(null));
        System.out.println(list.search(null));
        System.out.println(list);
        System.out.println(list.remove('A'));
        System.out.println(list);
        System.out.println(list.remove('C'));
        System.out.println(list);
        System.out.println(list.remove('A'));
        System.out.println(list);
        Iterator<Object> iter = list.iterator();
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(list.remove(null));
        System.out.println(list);
        System.out.println(list.search('A'));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.removeFirst();
        list.removeLast();
        System.out.println(list.size());
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.remove('B'));
        System.out.println(list);
        System.out.println(list.size());
        list.clear();
        System.out.println(list);
        System.out.println(iter.hasNext());
        list.removeFirst();
        System.out.println(list);
        list.removeLast();
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.search('B'));
        System.out.println(list.size());
        list.addFirst('A');
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }
}
