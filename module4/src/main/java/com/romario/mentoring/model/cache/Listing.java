package com.romario.mentoring.model.cache;

/**
 * Listing model class
 */
public class Listing {

    private final long id;
    private final String title;
    private final Ratio ratio;

    public Listing(final long id, final String title, final Ratio ratio) {
        this.id = id;
        this.title = title;
        this.ratio = ratio;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Listing)) return false;

        Listing listing = (Listing) o;

        return id == listing.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Listing {" +
                "id: " + id +
                ", title: '" + title + '\'' +
                ", ratio: " + ((ratio == null) ? null : ratio.getValue()) +
                '}';
    }
}
