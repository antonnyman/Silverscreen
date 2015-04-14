package se.k3.antonochisak.silverscreen.models;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by anton on 2015-04-14.
 */
@Parcel
public class Movie {

    String title, slugline, poster, fanart;
    int year;

    @ParcelConstructor
    public Movie(String title, String slugline, String poster, String fanart, int year) {
        this.title = title;
        this.slugline = slugline;
        this.poster = poster;
        this.fanart = fanart;
        this.year = year;
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
}
