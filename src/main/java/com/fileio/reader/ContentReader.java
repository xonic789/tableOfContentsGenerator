package com.fileio.reader;

import com.fileio.model.Contents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ContentReader {
    private BufferedReader bufferedReader;

    private ContentReader(){}

    public ContentReader(String path) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public List<String> read() throws IOException {
        List<String> contents = new ArrayList<>();
        while (true){
            String line = bufferedReader.readLine();
            if (line == null) break;
            contents.add(line);
        }
        return contents;
    }




}
