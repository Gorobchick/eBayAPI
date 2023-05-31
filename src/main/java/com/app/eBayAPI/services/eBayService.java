package com.app.eBayAPI.services;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.eBayAPI.models.Item;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.app.eBayAPI.controller.EbayAuthController;

@Service
public class eBayService {

    String Token;

    EbayAuthController ebayAuthController = new EbayAuthController(Token);

    OAuth2Api oauth2Api = new OAuth2Api();


    Item item = new Item(null, null, 0, 0) ;

    private final String eBayApiEndpoint = "https://api.ebay.com/";
    private final String eBayAuthToken = Token;

    //Створення товару
    public void createItem(Item item) {
        String url = eBayApiEndpoint + "/sell/inventory/v1/item";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + eBayAuthToken);
        headers.set("Content-Type", "application/json");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Item> requestEntity = new HttpEntity<>(item, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Item created successfully on eBay.");
        } else {
            System.out.println("Failed to create item on eBay. Error: " + responseEntity.getBody());
        }
    }

    //Оновлення товару

    public void updateItem(String itemId, Item updatedItem) {
        String url = eBayApiEndpoint + "/sell/inventory/v1/item/" + itemId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + eBayAuthToken);
        headers.set("Content-Type", "application/json");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Item> requestEntity = new HttpEntity<>(updatedItem, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Item updated successfully on eBay.");
        } else {
            System.out.println("Failed to update item on eBay. Error: " + responseEntity.getBody());
        }
    }

    //Списання товару
    public void cancelOrder(String itemId) {
        String url = eBayApiEndpoint + "/sell/inventory/v1/item/" + itemId + "/cancel";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + eBayAuthToken);
        
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Item canceled successfully on eBay.");
        } else {
            System.out.println("Failed to cancel item on eBay. Error: " + responseEntity.getBody());
        }
    }

    //Видалення товару
    public void deleteItem(String itemId) {
        String url = eBayApiEndpoint + "/sell/inventory/v1/item/" + itemId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + eBayAuthToken);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Item deleted successfully from eBay.");
        } else {
            System.out.println("Failed to delete item from eBay. Error: " + responseEntity.getBody());
        }
    }
}
