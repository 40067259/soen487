package com.example.a1;

import com.example.a1.artistClient.artistClient;
import com.example.a1.rest.Artist;

import java.util.Scanner;

public class client {

    public static void main(String[] args)
    {
        artistClient ac = new artistClient();
        String nickName;
        String firstName;
        String lastName;
        String bio;
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
                case 6:
                    System.out.println("have a nice day ");
                    keyboard.close();
                    System.exit(0);
                default:
                    System.out.println("invalid input, try again ");

            }
        }

    }

    public static void showMenu()
    {
       System.out.println("1. get all artists"+"\n2.get a specific artist"+"\n3.add a artist"+"\n4.update a artist"+"\n5.delete a artist"+"\n6.quit");
    }

}

