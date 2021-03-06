package com.fileio;

import com.fileio.model.Contents;
import com.fileio.reader.ContentReader;
import com.fileio.service.OrderedListStyle;
import com.fileio.service.TableOfContentsGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("목차 생성기 1 : 파일 경로 2 : 내용 3 : 종료 >> ");
        bw.flush();
        String how = br.readLine();
        while (true){
            if (how.equals("1")){
                bw.write("파일 경로 >> ");
                bw.flush();
                String path = br.readLine();
                ContentReader contentReader = new ContentReader(path);
                List<String> contents = contentReader.read();
                TableOfContentsGenerator generator = new OrderedListStyle(contents);
                List<String> tableOfContent = generator.createTableOfContent();
                for (String s : tableOfContent) {
                    bw.write(s);
                    bw.write("\n");
                }
                bw.flush();
                break;
            }else if (how.equals("2")){
                bw.write("파일 내용 >>");
                bw.flush();
                ContentReader contentReader = new ContentReader();
                List<String> contents = contentReader.read();
                TableOfContentsGenerator generator = new OrderedListStyle(contents);
                List<String> tableOfContent = generator.createTableOfContent();
                for (String s : tableOfContent) {
                    bw.write(s);
                    bw.write("\n");
                }
                bw.flush();
                break;
            }else if (how.equals("3")){
                bw.write("종료 되었습니다");
                bw.flush();
                break;
            }else {
                bw.write("잘못 입력하셨습니다");
                break;
            }

        }
    }
}

