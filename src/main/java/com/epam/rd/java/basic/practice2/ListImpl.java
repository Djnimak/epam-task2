package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    protected int modCount = 0;

    private Node<Object> first;
    private Node<Object> last;
    public int listSize;

    ListImpl() {
        first = null;
        last = null;
        listSize = 0;
    }

    static class Node<Object> {
        Object data;
        ListImpl.Node<Object> next;
        ListImpl.Node<Object> prev;

        Node(ListImpl.Node<Object> prev, Object data, ListImpl.Node<Object> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Object getElement() {
            return this.data;
        }
    }

    @Override
    public void clear() {
        ListImpl.Node<Object> next;
        for (ListImpl.Node<Object> x = this.first; x != null; x = next) {
            next = x.next;
            x.data = null;
            x.prev = null;
            x.next = null;
        }

        this.first = this.last = null;
        this.listSize = 0;
        ++this.modCount;
    }

    @Override
    public int size() {
        return this.listSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private ListImpl.Node<Object> lastReturned;
        private ListImpl.Node<Object> next;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return this.nextIndex < ListImpl.this.listSize;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                this.lastReturned = this.next;
                this.next = this.next.next;
                ++this.nextIndex;
                return this.lastReturned.data;
            }
        }

        @Override
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else {
                ListImpl.Node<Object> lastNext = this.lastReturned.next;
                ListImpl.this.unlink(this.lastReturned);
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
        ListImpl.Node<Object> f = this.first;
        ListImpl.Node<Object> newNode = new ListImpl.Node<>(null, element, f);
        this.first = newNode;
        if (f == null) {
            this.last = newNode;
        } else {
            f.prev = newNode;
        }
        ++this.listSize;
        ++this.modCount;
    }

    @Override
    public void addLast(Object element) {
        ListImpl.Node<Object> l = this.last;
        ListImpl.Node<Object> newNode = new ListImpl.Node<>(l, element, null);
        this.last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
        ++this.listSize;
        ++this.modCount;
    }

    public void unlinkFirst(Node<Object> f) {
        ListImpl.Node<Object> next = f.next;
        f.data = null;
        f.next = null;
        this.first = next;
        if (next == null) {
            this.last = null;
        } else {
            next.prev = null;
        }
        --this.listSize;
        ++this.modCount;
    }

    public void unlinkLast(Node<Object> l) {
        ListImpl.Node<Object> prev = l.prev;
        l.data = null;
        l.prev = null;
        this.last = prev;
        if (prev == null) {
            this.first = null;
        } else {
            prev.next = null;
        }
        --this.listSize;
        ++this.modCount;
    }

    void unlink(Node<Object> x) {
        ListImpl.Node<Object> next = x.next;
        ListImpl.Node<Object> prev = x.prev;
        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.data = null;
        --this.listSize;
        ++this.modCount;
    }

    @Override
    public void removeFirst() {
        ListImpl.Node<Object> f = this.first;
        if (f == null) {
            throw new NoSuchElementException();
        } else {
            this.unlinkFirst(f);
        }
    }

    @Override
    public void removeLast() {
        ListImpl.Node<Object> l = this.last;
        if (l == null) {
            throw new NoSuchElementException();
        } else {
            this.unlinkLast(l);
        }
    }

    @Override
    public Object getFirst() {
        ListImpl.Node<Object> f = this.first;
        if (f == null) {
            throw new NoSuchElementException();
        } else {
            return f.data;
        }
    }

    @Override
    public Object getLast() {
        ListImpl.Node<Object> l = this.first;
        if (l == null) {
            throw new NoSuchElementException();
        } else {
            return l.data;
        }
    }

    @Override
    public Object search(Object element) {
        ListImpl.Node<Object> x;
        Object e = null;
        if (element == null) {
            for (x = this.first; x != null; x = x.next) {
                if (x.data == null) {
                    break;
                }
            }
        } else {
            for (x = this.first; x != null; x = x.next) {
                if (element.equals(x.data)) {
                    e = x.data;
                }
            }
        }
        return e;
    }

    @Override
    public boolean remove(Object element) {
        ListImpl.Node<Object> x;
        if (element == null) {
            for (x = this.first; x != null; x = x.next) {
                if (x.data == null) {
                    this.unlink(x);
                    return true;
                }
            }
        } else {
            for (x = this.first; x != null; x = x.next) {
                if (element.equals(x.data)) {
                    this.unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (listSize == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder("[");
        Node<Object> current = first;
        while (current != last) {
            s.append(current.getElement());
            s.append(", ");
            current = current.next;
        }
        s.append(current.getElement());
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        ListImpl.IteratorImpl iter = list.new IteratorImpl();
        list.addFirst('A');
        list.addLast('B');
        list.addLast('C');
        System.out.println(iter.hasNext());
        System.out.println(iter.next);
        list.removeFirst();
        list.removeLast();
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.search('B'));
        System.out.println(list.size());
        list.addFirst('A');
        list.remove('A');
        System.out.println(list);
        list.clear();
    }
}
