package se.k3.antonochisak.silverscreen.models;

import org.parceler.ParcelConstructor;

/**
 * Created by isak on 2015-04-23.
 */
public class Movie {

    // private
    private String title, slugline, poster, fanart;
    private int year;
    private long votes;

    // private
    @ParcelConstructor
    private Movie(Builder builder) {
        this.title = builder.title;
        this.slugline = builder.slugline;
        this.poster = builder.poster;
        this.fanart = builder.fanart;
        this.year = builder.year;
        this.votes = builder.votes;
    }

    // getters because with a builder pattern the variables needs to be private
    public String getTitle() {
        return title;
    }

    public String getSlugline() {
        return slugline;
    }

    public String getPoster() {
        return poster;
    }

    public String getFanart() {
        return fanart;
    }

    public int getYear() {
        return year;
    }

    public long getVotes() {
        return votes;
    }

    public static class Builder {
        private String title, slugline, poster, fanart;
        private int year;
        private long votes;

        public Builder title (String title) {
            this.title = title;
            return this;
        }

        public Builder slugLine (String slugLine) {
            this.slugline = slugLine;
            return this;
        }

        public Builder poster (String poster) {
            this.poster = poster;
            return this;
        }

        public Builder fanart (String fanart) {
            this.fanart = fanart;
            return this;
        }

        public Builder year (int year) {
            this.year = year;
            return this;
        }

        public Builder votes (int votes) {
            this.votes = votes;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
