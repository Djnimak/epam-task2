package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    ArrayImpl stack = new ArrayImpl();


    StackImpl() {
        new ArrayImpl(0);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < stack.size && stack.get(currentIndex) != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Object o = null;
                for (int i = 0; i <= currentIndex; i++) {
                    o = stack.get(i);
                }
                currentIndex++;
                return o;
            }
        }

        @Override
        public void remove() {
            stack.get(currentIndex - 1);
            currentIndex--;
        }

    }

    @Override
    public void push(Object element) {
        stack.add(element);

    }

    @Override
    public Object pop() {
        Object o = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return o;
    }

    @Override
    public Object top() {
        return stack.get(stack.size() - 1);
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        Iterator<Object> iter = stack.iterator();
        stack.push('A');
        stack.push('B');
        stack.push('C');
        System.out.println(stack);
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(stack.size());
        System.out.println(stack.top());
        stack.pop();
        stack.clear();
    }

}
