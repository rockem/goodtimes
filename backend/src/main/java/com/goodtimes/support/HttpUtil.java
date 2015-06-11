package com.goodtimes.support;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HttpUtil {

    public static HttpHeaders createPostHttpHeaders(String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri());
        return httpHeaders;
    }
}
