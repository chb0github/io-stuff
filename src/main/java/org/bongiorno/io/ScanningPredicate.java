package org.bongiorno.io;



import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.function.Predicate;

/**
 * @author chribong
 */
public class ScanningPredicate implements Predicate<Integer> {

    private BitSet current = new BitSet();
    private BitSet lookingFor;
    private int bitCount;

    public ScanningPredicate(byte[] lookingFor) {
        this.lookingFor = BitSet.valueOf(reverse(lookingFor));
        this.bitCount = (8 * lookingFor.length);
    }

    public ScanningPredicate(String lookingFor) {
        this(lookingFor.getBytes());
    }

    public ScanningPredicate(String lookingFor, Charset charSet) {
        this(lookingFor.getBytes(charSet));
    }


    @Override
    public boolean test(Integer read) {
        boolean match = false;
        if (read != -1) {
            shiftLeft(current, 8);
            current.or(BitSet.valueOf(new byte[]{read.byteValue()}));
            if (current.size() > bitCount) {
                current.clear(bitCount, current.size());
            }

            match = current.equals(lookingFor);
        }
        return match;
    }

    public void shiftLeft(BitSet bs, int n) {
        for (int i = bs.length(); i >= 0; --i) {
            bs.set(i + n, bs.get(i));
        }
        bs.clear(0, n);
    }

    private byte[] reverse(byte[] input) {
        byte[] copy = null;
        if (input != null) {
            copy = new byte[input.length];
            for (int i = 0; i < input.length; i++) {
                copy[i] = input[input.length - i - 1];
            }
        }
        return copy;
    }
}
