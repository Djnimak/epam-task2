package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] arrayList;
    int size;
    int capcaity;
    int length = 0;

    public ArrayImpl() {
        this(0);
    }

    public ArrayImpl(int capcaity) {
        this.capcaity = capcaity;
        arrayList = new Object[capcaity];
    }

    @Override
    public void clear() {
        arrayList = new Object[0];
        capcaity = 0;
        length = 0;
    }

    @Override
    public int size() {
        size = length;
        return length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex == 0) {
                return currentIndex < capcaity && arrayList[currentIndex] != null;
            } else {
                return currentIndex < capcaity;
            }
        }

        @Override
        public Object next() {
            Object o;
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                o = arrayList[currentIndex];
            }
            currentIndex++;
            return o;
        }

        @Override
        public void remove() {
//            arrayList[currentIndex - 1] = null;
//            currentIndex--;
        }
    }

    @Override
    public void add(Object element) {
        Object[] temp;
        if (capcaity == 0) {
            temp = new Object[capcaity + 1];
            temp[size()] = element;
            arrayList = temp;
            capcaity++;
        } else {
            if (length == capcaity) {
                growSize();
            } else {
                for (int i = 1; i < capcaity; i++) {
                    if (arrayList[i] != null) {
                        arrayList[i - 1] = arrayList[i];
                        arrayList[i] = null;
                    }
                }
            }
            arrayList[capcaity - 1] = element;
        }
        length++;
    }

    public void growSize() {
        Object[] temp = new Object[capcaity + 1];
        System.arraycopy(arrayList, 0, temp, 0, capcaity);
        arrayList = temp;
        capcaity++;
    }

    @Override
    public void set(int index, Object element) {
        arrayList[index] = element;
    }

    @Override
    public Object get(int index) {
        if ((index < 0 || index >= size())) {
            throw new IndexOutOfBoundsException("Out of bounds");
        } else {
            if (arrayList[index] != null) {
                return arrayList[index];
            } else {
                return null;
            }
        }
    }

    @Override
    public int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < capcaity - 1; i++) {
            if (arrayList[i] != null && arrayList[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void remove(int index) {
        Object[] temp;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        temp = new Object[capcaity - 1];
        System.arraycopy(arrayList, 0, temp, 0, index);
        if (index != length - 1) {
            System.arraycopy(arrayList, index + 1, temp, index, (capcaity - index) - 1);
        }
        arrayList = temp;
        length--;
        capcaity--;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
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
        ArrayImpl array = new ArrayImpl(0);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        array.add('A');
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        array.add(null);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        System.out.println("-------");
        array.add('C');
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        array.add(null);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        System.out.println(array.get(1));
        Iterator<Object> iter = array.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        array.remove(1);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        System.out.println(array.get(1));
    }
}
