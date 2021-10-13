package com.sunner.imageatagger.controller;

import com.sunner.imageatagger.database.EntryRepo;
import com.sunner.imageatagger.database.model.Entry;
import com.sunner.imageatagger.model.Mode;
import com.sunner.imageatagger.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageApiClient {

    EntryRepo entryRepo;
    final String BASE_API_URL = "https://api.imagga.com/v2/";

    @Value("${auth-key}")
    private String authorizationKey;

    @Autowired
    public ImageApiClient(EntryRepo entryRepo) {
        this.entryRepo = entryRepo;
    }

    private ImageResponse getImageResponseFromApi(Mode mode, String imageUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = this.getHttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String requestUrl = getRequestUrl(mode, imageUrl);
        ResponseEntity<ImageResponse> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, ImageResponse.class);
        return responseEntity.getBody();
    }

    private String getRequestUrl(Mode mode, String imageUrl) {
        String requestUrl = BASE_API_URL;
        if (mode != Mode.TAGS)
            requestUrl += "categories/";

        requestUrl += mode.getCode() + "?image_url="
                + imageUrl;
        return requestUrl;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("accept", "application/json");
        httpHeaders.add("authorization", this.authorizationKey);
        return httpHeaders;
    }

    private CategoriesItem getBadCatItem(String cat, Double perc) {
        CategoriesItem categories = new CategoriesItem();
        Name name = new Name();
        name.setEn(cat);
        categories.setConfidence(perc);
        categories.setName(name);
        return categories;
    }

    private ImageResponse getBadResponse(){
        ImageResponse imageResponse = new ImageResponse();
        Result result = new Result();
        Status status = new Status();
        status.setText("failed");
        result.setCategories(Arrays.asList(getBadCatItem("PAGE", 4.00),
                getBadCatItem("NOT", 0.0),
                getBadCatItem("FOUND", 4.0)));
        imageResponse.setResult(result);
        imageResponse.setStatus(status);
        return imageResponse;
    }

    @GetMapping("/entries")
    public List<Entry> getAllEntries() {
        List<Entry> entries = entryRepo.findAll();
        return entries;
    }

    private String getCurrentDateFormatted(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        return formatDateTime;
    }

    @PostMapping("/tag")
    public ImageResponse getImageResponse(@RequestBody Map<String, String> json) throws InterruptedException {

        Mode mode = Mode.valueOf(json.get("mode"));
        String url = json.get("url");
        String dateTime = getCurrentDateFormatted();

        ImageResponse imageResponse;
        try {
            imageResponse = this.getImageResponseFromApi(mode, url);
        }catch(Exception e){
            return getBadResponse();
        }

        try {
            entryRepo.save(new Entry(url, dateTime));
        }catch(Exception e){
            e.printStackTrace();
        }

        return imageResponse;
    }
}
