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

        public static void getAlbum() throws IOException
        {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(String.format("http://localhost:8080/jersey/albums"));
                    CloseableHttpResponse response = client.execute(httpget);
                    ReadResponse(response);
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static void getAlbumbyID(String isrc) throws IOException
            {
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpGet httpget = new HttpGet(String.format("http://localhost:8080/jersey/get/%s", isrc));
                    CloseableHttpResponse response = client.execute(httpget);
                    ReadResponse(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public static void addAlbum (String isrc, String title, String description, String year, String artist) throws
            IOException {
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/jersey/createAlbum/%s/%s/%s/%s/%s", isrc, title, description, year, artist));
                    CloseableHttpResponse httpResponse = client.execute(httpPost);
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Fail to add album.");
                }
            }

            public static void updateAlbum(String isrc, String title, String description, String year, String artist) throws IOException{
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpPut httpput = new HttpPut(String.format("http://localhost:8080/jersey/modifyAlbum/%s/%s/%s/%s/%s", isrc, title, description, year, artist));
                    CloseableHttpResponse httpResponse = client.execute(httpput);
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Fail to update album.");
                }
            }

            public static void deleteAlbum (String isrc) throws IOException{
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/jersey/deleteAlbum/%s", isrc));
                    CloseableHttpResponse httpResponse = client.execute(httpDelete);
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Fail to delete album.");
                }
            }

    }

