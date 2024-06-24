package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://api.nasa.gov/planetary/apod?api_key=qkeb8VaaPB3dcXy56BDbKmjBzGscMxaaVJre9xjq&date=2024-06-13";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        // Scanner scanner = new Scanner(response.getEntity().getContent());
        // System.out.println(scanner.nextLine());

        ObjectMapper mapper = new ObjectMapper();
        NASAAnswer answer = mapper.readValue(response.getEntity().getContent(), NASAAnswer.class);

        String imageUrl = answer.url;
        String[] splittedUrl = imageUrl.split("/");
        String fileName = splittedUrl[splittedUrl.length - 1];
        HttpGet imageGet = new HttpGet(imageUrl);
        CloseableHttpResponse image = httpclient.execute(imageGet);

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        image.getEntity().writeTo(fileOutputStream);


        //System.out.println(answer.date);
        //System.out.println(answer.url);

        }
    }
