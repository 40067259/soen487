package com.example.a1.albumClient;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AlbumClient {

    public static String ReadResponse(CloseableHttpResponse response) throws IOException
    {
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while(sc.hasNext())
        {
            stringResponse.append(sc.nextLine());
            stringResponse.append("\n");
        }
        return stringResponse.toString();

    }

    public static void main(String[] args) throws IOException
    {

        System.out.println("Album client main method was called!");

//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        ResponseHandler<String> responseHandler = new MyResponseHandler();
//
//        HttpGet httpGet = new HttpGet("http://localhost:8080/jersey");
//
//        String httpResponse = httpClient.execute(httpGet, responseHandler);
    }

        public static String getAlbum() throws IOException, IllegalArgumentException
        {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(String.format("http://localhost:8080/jersey/albums"));
                    CloseableHttpResponse response = client.execute(httpget);

                   // ReadResponse(response);
                Scanner scanner = new Scanner(response.getEntity().getContent());
                StringBuilder sb = new StringBuilder();
                while(scanner.hasNext()){
                    sb.append(scanner.nextLine());
                    sb.append("\n");
                }
                response.close();
                scanner.close();
                System.out.println(sb.toString());
            } catch (IOException e) {
                    e.printStackTrace();
                }
            return "null";
            }

            public static void getAlbumbyID(String isrc) throws IOException, IllegalArgumentException
            {
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(String.format("http://localhost:8080/jersey/get/%s", isrc));
                    CloseableHttpResponse response = client.execute(httpget);
//                    ReadResponse(response);
                    Scanner scanner = new Scanner(response.getEntity().getContent());


                    StringBuilder sb = new StringBuilder();
                    while(scanner.hasNext()){
                        sb.append(scanner.nextLine());
                        sb.append("\n");
                    }
                    response.close();
                    scanner.close();
                    System.out.println(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static void addAlbum (String isrc, String title, String description, String year, String artist) throws
            IOException, IllegalArgumentException{
                try (CloseableHttpClient client = HttpClients.createDefault())
                {
                    String NewD = description;
                    String NewA = artist;
                    if(description.contains(" "))
                    {
                        NewD = description.replaceAll(" ", "%20");
                    }
                    if(artist.contains(" "))
                    {
                        NewA = description.replaceAll(" ", "%20");
                    }
                    HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/jersey/createAlbum/%s/%s/%s/%s/%s", isrc, title, NewD, year, NewA));
                    CloseableHttpResponse httpResponse = client.execute(httpPost);
                    Scanner scanner = new Scanner(httpResponse.getEntity().getContent());


                    StringBuilder sb = new StringBuilder();
                    while(scanner.hasNext()){
                        sb.append(scanner.nextLine());
                        sb.append("\n");
                    }
                    scanner.close();
                    System.out.println(sb.toString());
                    httpResponse.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Fail to add album.");
                }

            }

            public static void updateAlbum(String isrc, String title, String description, String year, String artist) throws IOException, IllegalArgumentException{
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    String NewD = description;
                    String NewA = artist;
                    if(description.contains(" "))
                    {
                        NewD = description.replaceAll(" ", "%20");
                    }
                    if(artist.contains(" "))
                    {
                        NewA = artist.replaceAll(" ", "%20");
                    }
                    HttpPut httpput = new HttpPut(String.format("http://localhost:8080/jersey/modifyAlbum/%s/%s/%s/%s/%s", isrc, title, NewD, year, NewA));
                    CloseableHttpResponse httpResponse = client.execute(httpput);
                    Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
                    StringBuilder sb = new StringBuilder();
                    while(scanner.hasNext()){
                        sb.append(scanner.nextLine());
                        sb.append("\n");
                    }
                    scanner.close();
                    System.out.println(sb.toString());
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Fail to update album.");
                }
            }

            public static void deleteAlbum (String isrc) throws IOException, IllegalArgumentException{
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/jersey/deleteAlbum/%s", isrc));
                    CloseableHttpResponse httpResponse = client.execute(httpDelete);
                    Scanner scanner = new Scanner(httpResponse.getEntity().getContent());
                    StringBuilder sb = new StringBuilder();
                    while(scanner.hasNext()){
                        sb.append(scanner.nextLine());
                        sb.append("\n");
                    }
                    scanner.close();
                    System.out.println(sb.toString());
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Fail to delete album.");
                }
            }

    }

