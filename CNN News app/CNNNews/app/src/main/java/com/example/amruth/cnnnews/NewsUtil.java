package com.example.amruth.cnnnews;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by amruth on 13/02/17.
 */

public class NewsUtil {
    static public class NewClass extends DefaultHandler{
        ArrayList<News> newsList;
        StringBuilder sb;
        News news;

        public ArrayList<News> getNewsList() {
            return newsList;
        }

        static public ArrayList<News> newMethod(InputStream in) throws IOException, SAXException {
          NewClass parser= new NewClass();
          Xml.parse(in,Xml.Encoding.UTF_8,parser);
            return parser.getNewsList();



        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            newsList= new ArrayList<News>();
            sb= new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                news= new News();
            }else if(localName.equals("content")){
                if(attributes.getValue("width").equals(attributes.getValue("height"))){
                    news.setImageURL(attributes.getValue("url"));
                }

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("item")){
                newsList.add(news);
            }else if(localName.equals("title") && news!=null){
                news.setTitle(sb.toString().trim());

            }else if(localName.equals("description")&&news!=null){
                news.setDescription(sb.toString().trim());
            }else if(localName.equals("pubDate")&&news!=null){
                String dateString= sb.toString().trim();
                Date date=null;
                try {
                    date = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss zzz").parse(dateString);
                    news.setPubDate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch,start,length);
        }
    }
}
