package com.cooltechworks.creditcarddesign.interval;


public class Interval<T extends Comparable<T>> {

    private final T start;
    private final T end;

    public Interval(T start, T end) {
        if(start.compareTo(end) == 1){
            this.end = start;
            this.start = end;
        } else {
            this.start = start;
            this.end = end;
        }
    }

    public boolean isEntry(T value){
        int compareToStart = value.compareTo(start);
        int compareToEnd = value.compareTo(end);
        if(compareToStart >= 0 && compareToEnd <= 0 ){
            return true;
        }
        return false;
    }


    public boolean intersect(Interval<T> value){
        int compareEndToStart = value.getEnd().compareTo(start);
        int compareStartToEnd = value.getEnd().compareTo(end);
        if(compareEndToStart == -1 || compareStartToEnd ==1 ){
            return false;
        }
        return true;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }


    public int compareTo(T o) {
        if(o.compareTo(start) == -1){
            return -1;
        }
        if(o.compareTo(end) == 1){
            return 1;
        }
        return 0;
    }

    public int compareTo(Interval<T> o) {
        if(this.intersect(o)){
            return 0;
        }
        if(this.getEnd().compareTo(o.getStart()) == -1 && this.getEnd().compareTo(o.getEnd()) == -1){
            return -1;
        }
        if(this.getStart().compareTo(o.getEnd()) == 1 && this.getStart().compareTo(o.getStart()) == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;

        Interval<?> interval = (Interval<?>) o;

        if (getStart() != null ? !getStart().equals(interval.getStart()) : interval.getStart() != null) return false;
        return getEnd() != null ? getEnd().equals(interval.getEnd()) : interval.getEnd() == null;
    }

    @Override
    public int hashCode() {
        int result = getStart() != null ? getStart().hashCode() : 0;
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        return result;
    }
}
