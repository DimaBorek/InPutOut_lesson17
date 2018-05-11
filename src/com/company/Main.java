package com.company;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.*;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	readAndWriteByLines();
    }
    public static void justPead(){
        FileInputStream fis = null;
        try {
          //  fis = new FileInputStream("text/test.txt"); // если считываемый файл находиться в корне проекта
            fis = new FileInputStream("D:\\lesson17\\test.txt"); // если файл находиться где то на компе
            System.out.println("file size inbutes = "+fis.available());// считываем байтами
            int content;
            while ((content = fis.read())!=-1){
                System.out.print((char)content);

            }
        }catch (IOException e){
            System.out.println(e.getMessage());

        }finally {
            if(fis != null);

        }try {
            fis.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readWithResources(){//метод с autoCloseble(в котором не надо писать явно finnaly и close) с 7 версии Java
        try(FileInputStream fis = new FileInputStream("text/test.txt")){
        int content;
        while((content = fis.read()) !=-1){
            System.out.print((char)content);
        }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public static void readAndWrite(){
        try(FileInputStream fis = new FileInputStream("text/test.txt");
        FileOutputStream fos = new FileOutputStream("text/resoult.txt")){
            int content;
            while((content = fis.read())!=-1){
                System.out.print((char)content);
                fos.write(content);

            }
        }   catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
    public static void writeStringTofile(){//запись в файлы!
        try(FileOutputStream fos = new FileOutputStream("text/test.txt")){
            List<String> strings = new ArrayList<>();
            strings.add("one");
            strings.add("two");
            strings.add("three");
            strings.add("four");
            for (String s :strings){
                s+= "\n";
                fos.write(s.getBytes());
            }

        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }
    public static void bufferedInputStream() {// тут закидывает в буффер и метод клоус неявный!(написан вручную
        InputStream inStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bus = null;
        try {
            inStream = new FileInputStream("text/test.txt");
            bis = new BufferedInputStream(inStream);
            bus = new BufferedOutputStream(new FileOutputStream("text/buff_stream.txt"));
            while (bis.available() > 0) {
                char c = (char) bis.read();
                System.out.println("symbol = " + c);
                bus.write(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {//TODO неявный метод
            if (inStream != null && bis != null && bus != null) {
                try {
                    inStream.close();
                    bis.close();
                    bus.close();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static void bufferedInputStream1() {//тот же метод только с явным клоусом!
        try (FileInputStream inStream = new FileInputStream("text/test.txt");//TODO явный метод кслоус(CLOSE)
             BufferedInputStream bis = new BufferedInputStream(inStream);
             FileOutputStream fileOutputStream = new FileOutputStream("text/buff_stream.txt");
             BufferedOutputStream bus = new BufferedOutputStream(fileOutputStream)) {

            while (bis.available() > 0) {
                char c = (char) bis.read();
                System.out.println("symbol = " + c);
                bus.write(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readByLines(){//считывание строками, а не байтами
        File file = new File("text/test.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine())!=null){
                System.out.println(line);

            }
        }catch (IOException e ){
            System.out.println(e.getMessage());
        }
    }
    public static void readAndWriteByLines() {
    File file = new File("text/test.txt");
    try(BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter("text/buffered_res.txt"))){
        List<String> strings = new ArrayList<>();
        String line;
        while((line=br.readLine())!=null){

            strings.add(line);
        }
        Collections.sort(strings);//сортировка по алфавиту!(конструктор или метод Collections
        for(String s:strings){
            bw.write(s+"\n");

        }
    }catch (IOException e){
        System.out.println(e.getMessage());
    }

    }




}
