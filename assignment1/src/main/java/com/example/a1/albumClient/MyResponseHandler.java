package com.example.a1.albumClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

public class MyResponseHandler implements ResponseHandler<String> {

    @Override
    public String handleResponse(final HttpResponse httpResponse) throws ClientProtocolException, IOException {

        int status = httpResponse.getStatusLine().getStatusCode();
        if(status >= 200 && status < 300)
        {
            HttpEntity entity = httpResponse.getEntity();
            if(entity == null)
            {
                return "";
            }
            else
                return EntityUtils.toString(entity);
        }
        else
            return status + "";
    }


}
