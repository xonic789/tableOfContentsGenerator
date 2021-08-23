package com.fileio.service;

import com.fileio.model.Contents;
import com.fileio.reader.ContentReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class OrderedListStyle implements TableOfContentsGenerator {
    private Contents contents;
    // 헤더 나열할 기준
    private int standard;
    private int now;
    private int size = 1;

    public OrderedListStyle(List<String> contents){
        this.contents = new Contents(contents);
    }

    @Override
    public List<String> createTableOfContent() {
        List<String> headers = getHeaders();
        List<String> tableOfContent = new ArrayList<>();
        for (String header : headers){
            tableOfContent.add(addTab(header) + convertLink(header));
        }
        return tableOfContent;
    }

    public List<String> getHeaders() {
        List<String> content = contents.getContent();
        List<String> headers = content.stream().filter((line) -> line.contains("#")).filter(this::test).collect(Collectors.toList());
        initStandard(headers.get(0));

        return headers;
    }

    private String convertLink(String header){
        StringBuilder sb = new StringBuilder();
        for (char ch : header.substring(header.indexOf(" ") + 1).toLowerCase().toCharArray()){
            if (ch == ' ' || ch == '-') sb.append('-');
            else if (isNumber(ch) || isKorean(ch) || Character.isAlphabetic(ch)){
                sb.append(ch);
            }
        }
        return "(" + "#"+ sb + ")";
    }


    private void initStandard(String firstHeader){
        standard = getHashTagCount(getHashTag(firstHeader));
        now = standard;
    }

    private boolean test(String line) {
        for (char ch : getHashTag(line).toCharArray()) {
            if (ch != '#') return false;
        }
        return true;
    }

    private String addTab(String header){
        String[] tabs = {"", "\t", "\t\t", "\t\t\t", "\t\t\t\t", "\t\t\t\t\t"};
        int hashTagCount = getHashTagCount(getHashTag(header));
        String tmp = header.substring(header.indexOf(" ") + 1);

        // 1. 기준인 standard와 같을 때는 tab = 0
        // 2. standard 보다 클 때는 now에 count 넣고
        if (standard >= hashTagCount){
            if (isNumber(tmp.charAt(0))) header = ">"+ tmp.charAt(0) + ". " + "[" + "**" + tmp.substring(tmp.indexOf(" ") + 1) + "**" + "]";
            else header = ">" + size++ +". "+ "[" + "**" + tmp + "**" + "]";

            now = standard;
        }else {
            now = hashTagCount;
            header = ">"+ tabs[now - standard] +"- "+"[" + tmp + "]";
        }
        return header;
    }

    private String getHashTag(String header){
        return header.substring(0, header.indexOf(" "));
    }

    private int getHashTagCount(String hashTags){
        return hashTags.length();
    }

    private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    private boolean isKorean(char ch){
        return ch >= '가' && ch <= '힣';
    }
}
