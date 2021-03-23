package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    ListImpl queue;
    int size;

    public QueueImpl() {
        queue = new ListImpl(0);
    }

    public QueueImpl(int size) {
        this.size = size;
        new ListImpl(size);
    }

    @Override
    public void clear() {
        if (queue.listSize != 0) {
            queue.clear();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return queue.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        ListImpl.Node lastReturned;
        ListImpl.Node next;
        ListImpl.Node current;
        int nextIndex;
        ListImpl copy = queue;

        private IteratorImpl() {
            current = queue.first;
            next = queue.last;
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex < size;
        }

        @Override
        public Object next() {
            Object data;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                data = copy.getFirst();
                copy.removeFirst();
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
                queue.remove(this.lastReturned);
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
    public void enqueue(Object element) {
        queue.addLast(element);
        size++;
    }

    @Override
    public Object dequeue() {
        Object o = queue.getFirst();
        queue.removeFirst();
        size--;
        return o;
    }

    @Override
    public Object top() {
        return queue.getFirst();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        Iterator<Object> iter = queue.iterator();
        queue.enqueue('A');
        queue.enqueue('B');
        queue.enqueue('C');
        queue.enqueue(null);
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        System.out.println(queue);
        System.out.println(iter.hasNext());
        System.out.println(queue.size);
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(queue.top());
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        queue.clear();
        System.out.println(queue);
    }

}
