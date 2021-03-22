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
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] != null) {
                arrayList[i] = null;
            }
        }
        size = 0;
    }

    @Override
    public int size() {
//        int count = 0;
//        for (Object o : arrayList) {
//            if (o != null) {
//                count++;
//            }
//        }
        size = length;
        return size;
    }

    public void length() {
        this.length = capcaity;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex < capcaity) {
                return arrayList[currentIndex] != null;
            } else {
                return false;
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
            arrayList[currentIndex - 1] = null;
            currentIndex--;
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
            length++;
        } else {
            if (size() == 0) {
                arrayList[capcaity - 1] = element;
                length++;
            } else if (length == capcaity) {
                growSize();
                arrayList[capcaity - 1] = element;
            } else {
                for (int i = 1; i < capcaity; i++) {
                    if (arrayList[i] != null) {
                        arrayList[i - 1] = arrayList[i];
                        arrayList[i] = null;
                    }
                }
                this.arrayList[capcaity - 1] = element;
                length++;
            }
        }
    }

    public void growSize() {
        Object[] temp = new Object[capcaity + 1];
        System.arraycopy(arrayList, 0, temp, 0, capcaity);
        arrayList = temp;
        capcaity++;
        length++;
    }

    @Override
    public void set(int index, Object element) {
        arrayList[index] = element;
//        if (size == capcaity) {
//            growSize();
//        }
//        if (size - index >= 0) System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
//        arrayList[index] = element;
//        size++;
    }

    @Override
    public Object get(int index) {
        if ((index < 0 || index > size()) && arrayList[index] != null) {
            return arrayList[index];
        } else {
            return null;
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
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if (arrayList[index] != null) {
            arrayList[index] = null;
        }
        size--;
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
        ArrayImpl array = new ArrayImpl(2);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        array.add('A');
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
        array.add('B');
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
        array.add('D');
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.capcaity);
        System.out.println(array.length);
//        Iterator<Object> iter = array.iterator();
//        System.out.println(array.indexOf('A'));
//        System.out.println(array.get(1));
//        array.set(2, 'D');
//        System.out.println(array);
//        array.remove(2);
//        System.out.println(array);
//        array.size();
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        array.clear();
//        System.out.println(array);
    }
}
