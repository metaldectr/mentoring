package com.romario.mentoring.model.cache;

/**
 * Ration model class
 */
public class Ratio {

    private final long id;
    private final long value;

    public Ratio(final long id, final long value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ratio)) return false;

        Ratio ratio = (Ratio) o;

        return id == ratio.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Ratio {" +
                "id: " + id +
                ", value: " + value +
                '}';
    }

    public long getListingId() {
        return 0;//TODO: Remove later
    }
}
