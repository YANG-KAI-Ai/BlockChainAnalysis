package com.bit.BitcoinAnalysis.controller;
/**
 * This example uses the Apache HTTPComponents library.
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JavaExample {



    public String getNFTData() {
        String apiKey = "aIouxe1OWamegkiizkgT0iZMYOPzN8W4";
        String uri = "https://api.dappradar.com/4tsxo4vuhotaojtl/nfts/marketplaces";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
//        paratmers.add(new BasicNameValuePair("limit","10"));
        paratmers.add(new BasicNameValuePair("resultsPerPage","25"));

        String result = "";

        try {
            result = makeAPICall(uri, paratmers, apiKey);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public String getDerivativesData() {
        String uri = "https://api.coingecko.com/api/v3/derivatives";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
//        paratmers.add(new BasicNameValuePair("limit","10"));
//        paratmers.add(new BasicNameValuePair("resultsPerPage","10"));

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public String getGlobalData() {
        String uri = "https://api.coingecko.com/api/v3/global";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
//        paratmers.add(new BasicNameValuePair("limit","10"));
//        paratmers.add(new BasicNameValuePair("resultsPerPage","10"));

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public String getExchangeData() {
        String uri = "https://api.coingecko.com/api/v3/exchanges";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
//        paratmers.add(new BasicNameValuePair("limit","10"));
//        paratmers.add(new BasicNameValuePair("resultsPerPage","10"));

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public String getApyAndTvlData() {
        String uri = "https://api.yearn.finance/v1/chains/1/vaults/all";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
//        paratmers.add(new BasicNameValuePair("limit","10"));
//        paratmers.add(new BasicNameValuePair("resultsPerPage","10"));

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }
    public String getNewsData(String page) {
        String uri = "https://newsapi.org/v2/everything";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
//        paratmers.add(new BasicNameValuePair("start","1"));
        paratmers.add(new BasicNameValuePair("pageSize","10"));
        paratmers.add(new BasicNameValuePair("page",page));
        paratmers.add(new BasicNameValuePair("q","cryptocurrency OR ethereum OR litecoin OR bitcoin"));
        paratmers.add(new BasicNameValuePair("apiKey","5007b689e7654c21a2833efb2b015eef"));
        paratmers.add(new BasicNameValuePair("sortBy","publishedAt"));

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public static String makeDerivativesAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
//        request.addHeader("apiKey", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    public static String makeAPICall(String uri, List<NameValuePair> parameters, String apiKey)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-BLOBR-KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    public String getHotBlockChainPools() {
        String uri = "https://api.blockchain.info/pools?timespan=5days";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public String getHotBlockChainToken() {
        String uri = "https://api.blockchain.info/stats";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

        String result = "";

        try {
            result = makeDerivativesAPICall(uri, paratmers);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

}
