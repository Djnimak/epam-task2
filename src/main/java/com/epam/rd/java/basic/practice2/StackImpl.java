package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    ArrayImpl stack;
    int capacity;

    public StackImpl() {
        this(0);
        stack = new ArrayImpl(0);
    }

    public StackImpl(int capacity) {
        this.capacity = capacity;
        stack = new ArrayImpl(capacity);
    }

    @Override
    public void clear() {
        stack = new ArrayImpl(0);
        capacity = 0;
    }

    @Override
    public int size() {
        return stack.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        ArrayImpl copy = stack;
        int currentIndex = copy.length-1;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public Object next() {
            Object o;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                o = stack.get(currentIndex);
            }
            currentIndex--;
            return o;
        }

        @Override
        public void remove() {
            stack.remove(currentIndex - 1);
            currentIndex--;
        }

    }

    @Override
    public void push(Object element) {
        stack.add(element);
        capacity++;

    }

    @Override
    public Object pop() {
        Object o;
        if ((stack.size() - 1) < 0) {
            o = null;
        } else {
            o = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            capacity--;
        }
        return o;
    }

    @Override
    public Object top() {
        if ((stack.size() - 1) < 0) {
            return null;
        } else {
            return stack.get(stack.size() - 1);
        }
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push('A');
        System.out.println(stack.capacity);
        stack.push('B');
        System.out.println(stack.capacity);
        stack.push('C');
        System.out.println(stack.capacity);
        stack.push(null);
        System.out.println(stack);
        System.out.println(stack.capacity);
        Iterator<Object> iter = stack.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        System.out.println();
        System.out.println(stack.size());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        stack.clear();
        System.out.println(stack);
    }

}
