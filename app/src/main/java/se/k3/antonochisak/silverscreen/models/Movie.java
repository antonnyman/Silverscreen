package se.k3.antonochisak.silverscreen.models;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by anton on 2015-04-14.
 */
@Parcel
public class Movie implements Comparable<Movie> {

    String title, slugline, poster, fanart;
    int year;
    long votes;

    @ParcelConstructor
    public Movie(String title, String slugline, String poster, String fanart, int year, long votes) {
        this.title = title;
        this.slugline = slugline;
        this.poster = poster;
        this.fanart = fanart;
        this.year = year;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlugline() {
        return slugline;
    }

    public void setSlugline(String slugline) {
        this.slugline = slugline;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFanart() {
        return fanart;
    }

    public void setFanart(String fanart) {
        this.fanart = fanart;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    @Override
    public int compareTo(Movie movie) {
        return 0;
    }
}
