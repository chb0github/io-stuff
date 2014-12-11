package org.bongiorno.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CompositeOutputStream extends OutputStream implements Collection<OutputStream> {

    Collection<OutputStream> delegates;

    public CompositeOutputStream(OutputStream... delegates) {
        this.delegates = Arrays.asList(delegates);
    }

    public CompositeOutputStream(Collection<OutputStream> delegates) {
        this.delegates = delegates;
    }

    @Override
    public void close() throws IOException {
        for (OutputStream delegate : delegates) {
            delegate.close();
        }
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream delegate : delegates) {
            delegate.flush();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (OutputStream delegate : delegates) {
            delegate.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream delegate : delegates) {
            delegate.write(b, off, len);
        }
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream delegate : delegates) {
            delegate.write(b);
        }
    }

    ///////////////////////////////////////////////Collection/////////////////////////////////
    @Override
    public boolean add(OutputStream outputStream) {
        return delegates.add(outputStream);
    }

    @Override
    public boolean addAll(Collection<? extends OutputStream> c) {
        return delegates.addAll(c);
    }

    @Override
    public void clear() {
        delegates.clear();
    }

    @Override
    public boolean contains(Object o) {
        return delegates.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegates.containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return delegates.equals(o);
    }

    @Override
    public int hashCode() {
        return delegates.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return delegates.isEmpty();
    }

    @Override
    public Iterator<OutputStream> iterator() {
        return delegates.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return delegates.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return delegates.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegates.retainAll(c);
    }

    @Override
    public int size() {
        return delegates.size();
    }

    @Override
    public Object[] toArray() {
        return delegates.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegates.toArray(a);
    }
}
