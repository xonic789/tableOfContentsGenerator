package com.fileio.model;

import java.util.ArrayList;
import java.util.List;

public class Contents {
    private List<String> content;
    private List<String> headers;

    public Contents(List<String> content){
        this.content = content;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
