package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] arrayList;
    int size;
    int capcaity;


    private ArrayImpl() {
        capcaity = 0;
        size = 0;
        arrayList = new Object[capcaity];
    }

    private ArrayImpl(int capcaity) {
        this.capcaity = capcaity;
        size = 0;
        arrayList = new Object[capcaity];
    }

    @Override
    public void clear() {
        for (int i = 0; i < arrayList.length; i++) {
            arrayList[i] = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size && arrayList[currentIndex] != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Object o = null;
                for (int i = 0; i <= currentIndex; i++) {
                    o = arrayList[i];
                }
                currentIndex++;
                return o;
            }
        }

        @Override
        public void remove() {
            arrayList[currentIndex - 1] = null;
            currentIndex--;
        }
    }

    @Override
    public void add(Object element) {
        Object[] temp = null;
        if (size == capcaity) {
            temp = new Object[capcaity + 1];
            if (capcaity >= 0) System.arraycopy(arrayList, 0, temp, 0, capcaity);
        }
        arrayList = temp;
        capcaity += 1;
        assert arrayList != null;
        arrayList[size] = element;
        size++;
    }

    public void growSize() {
        Object[] temp = null;
        if (size == capcaity) {
            temp = new Object[capcaity + 1];
            if (capcaity >= 0) System.arraycopy(arrayList, 0, temp, 0, capcaity);
        }
        arrayList = temp;
        capcaity += 1;
    }

    @Override
    public void set(int index, Object element) {
        if (size == capcaity) {
            growSize();
        }
        if (size - index >= 0) System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = element;
        size++;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return arrayList[index];
    }

    @Override
    public int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        arrayList[index] = null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] == null) {
                s.append("null");
            } else {
                s.append(arrayList[i]);
            }
            if (i != arrayList.length - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl(4);
        Iterator<Object> iter = array.iterator();
        array.add('A');
        array.add('B');
        array.add('C');
        System.out.println(array.indexOf('A'));
        System.out.println(array.get(1));
        array.set(2, 'D');
        array.remove(2);
        System.out.println(array);
        array.size();
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        array.clear();
    }
}
