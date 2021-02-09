package com.example.a1;

import com.example.a1.artistClient.artistClient;
import com.example.a1.rest.Artist;
import com.example.a1.albumClient.AlbumClient;

import java.io.IOException;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws IOException, IllegalArgumentException{
        artistClient ac = new artistClient();
        String nickName;
        String firstName;
        String lastName;
        String bio;
        String isrc;
        String title;
        String description;
        String year;
        String artist;

        showMenu();
        int choice = 0;
        Scanner keyboard = new Scanner(System.in).useDelimiter("\n");
        while(true)
        {
            boolean valid = false;
            while(!valid)
            {
                try
                {
                    choice = keyboard.nextInt();
                    valid = true;
                }catch(Exception e)
                {
                    System.out.println("please enter a valid integer ");
                    valid = false;
                    keyboard.nextLine();
                }
            }
            switch (choice)
            {
                case 1:
                    System.out.println("following is all artists ");
                    ac.getAllArtists();
                    showMenu();
                    break;
                case 2:
                    System.out.println("please enter the nick name of the artist ");
                    nickName = keyboard.next();
                    ac.getArtist(nickName);
                    showMenu();
                    break;
                case 3:
                    System.out.println("enter the artist nick name");
                    nickName = keyboard.next();
                    System.out.println("enter the artist first name");
                    firstName = keyboard.next();
                    System.out.println("enter the artist last name");
                    lastName = keyboard.next();
                    System.out.println("enter the artist bio");
                    bio = keyboard.next();
                    Artist newArtist = new Artist(nickName,firstName,lastName,bio);
                    ac.addArtist(newArtist);
                    showMenu();
                    break;
                case 4:
                    System.out.println("enter the artist nick name");
                    nickName = keyboard.next();
                    System.out.println("enter the artist first name");
                    firstName = keyboard.next();
                    System.out.println("enter the artist last name");
                    lastName = keyboard.next();
                    System.out.println("enter the artist bio");
                    bio = keyboard.next();
                    Artist upDateArtist = new Artist(nickName,firstName,lastName,bio);
                    ac.updateArtist(upDateArtist);
                    showMenu();
                    break;
                case 5:
                    System.out.println("please enter the nick name of the artist ");
                    nickName = keyboard.next();
                    ac.deleteArtist(nickName);
                    showMenu();
                    break;
                case 11:
                    System.out.println("have a nice day ");
                    keyboard.close();
                    System.exit(0);
                case 6:
                    System.out.println("following is all albums:");
                    System.out.println();
                    AlbumClient.getAlbum();
                    showMenu();
                    break;
                case 7:
                    System.out.println("please enter isrc of the album ");
                    isrc = keyboard.next();
                    AlbumClient.getAlbumbyID(isrc);
                    showMenu();
                    break;
                case 8:
                    System.out.println("enter the isrc");
                    isrc = keyboard.next();
                    System.out.println("enter the title");
                    title = keyboard.next();
                    System.out.println("enter the description");
                    description = keyboard.next();
                    System.out.println("enter the year");
                    year = keyboard.next();
                    System.out.println("enter the artist");
                    artist = keyboard.next();
                    if(description == null || description.length() == 0) description = "No Description for now";

                    if(isrc.length() != 0 && title.length() != 0 && year.length() != 0&& artist.length() != 0)
                        AlbumClient.addAlbum(isrc, title, description, year, artist);
                    else{
                        System.out.println("Your input information is incomplete, try again");
                        System.out.println();
                    }
                    showMenu();
                    break;
                case 9:
                    System.out.println("enter the isrc");
                    isrc = keyboard.next();
                    System.out.println("enter the title");
                    title = keyboard.next();
                    System.out.println("enter the description");
                    description = keyboard.next();
                    System.out.println("enter the year");
                    year = keyboard.next();
                    System.out.println("enter the artist");
                    artist = keyboard.next();
                    description = description.length() == 0 ? "No description for now" : description;
                    if(title.length() != 0 && artist.length() != 0 && year.length() != 0 && isrc.length() != 0)
                    AlbumClient.updateAlbum(isrc, title, description, year, artist);
                    else{
                        System.out.println("Your input information is incomplete, please try again");
                    }
                    showMenu();
                    break;
                case 10:
                    System.out.println("please enter the isrc");
                    isrc = keyboard.next();
                    AlbumClient.deleteAlbum(isrc);
                    showMenu();
                    break;
                default:
                    System.out.println("invalid input, try again ");

            }
        }

    }

    public static void showMenu()
    {
       System.out.println("1.get all artists."+"\n2.get a specific artist."+"\n3.add a artist."+"\n4.update a artist."+"\n5.delete a artist."+"\n6.get all albums.\n7.get album by isrc.\n8.add a new album.\n9.update an album.\n10.delete an album.\n11.quit");
    }

}

