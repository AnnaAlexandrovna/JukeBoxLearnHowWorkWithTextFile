package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class JukeBox implements Comparable<JukeBox>{
    String title;
    String artist;
    String rating;
    String bpm;
    ArrayList<JukeBox> songList = new ArrayList<JukeBox>();

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getRating() {
        return rating;
    }

    public String getBpm() {
        return bpm;
    }

    public JukeBox(){}
    public JukeBox(String title, String artist, String rating, String bpm) {
        this.title = title;
        this.artist = artist;
        this.rating = rating;
        this.bpm = bpm;
    }

    public boolean equals (Object aSong){
        JukeBox song = (JukeBox) aSong;
        return getTitle().equals(song.getTitle());
    }

    public int hashCode(){
        return title.hashCode();
    }


    public int compareTo(JukeBox song){
        return title.compareTo(song.getTitle());
    }

    public void getSong(){
        try {
            File file = new File("SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line=reader.readLine())!= null){
                addSong(line);
            }
        }catch (Exception ex){ex.printStackTrace();}

    }

    public String toString(){
        return title;
        //return title +" : "+ artist;
    }

    public void addSong(String lineToParse){
        String[] tokens = lineToParse.split("/");

        JukeBox nextSong = new JukeBox(tokens[0],tokens[1],tokens[2],tokens[3]);
        songList.add(nextSong);
    }

    public void go(){
        getSong();
        System.out.println(songList);
        Collections.sort(songList);
        //System.out.println(songList);
        ArtistCompare artistCompare = new ArtistCompare();
        Collections.sort(songList, artistCompare);
        System.out.println(songList);
        HashSet<JukeBox> songSet = new HashSet<JukeBox>();
        songSet.addAll(songList);
        System.out.println(songSet);
        TreeSet<JukeBox> songTree = new TreeSet<JukeBox>();
        songTree.addAll(songList);
        System.out.println(songTree);
    }

    class ArtistCompare implements Comparator<JukeBox>{
        public int compare(JukeBox one, JukeBox two){
            return  one.getArtist().compareTo(two.getArtist());
        }
    }

    public static void main(String[] args) {
	new JukeBox().go();
    }
}
