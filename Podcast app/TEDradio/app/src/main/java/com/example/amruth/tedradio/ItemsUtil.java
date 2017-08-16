package com.example.amruth.tedradio;

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
 * Created by amruth on 11/03/17.
 */

public class ItemsUtil {
    static public class NewClass extends DefaultHandler{
        ArrayList<Item> itemsList;
        StringBuilder sb;
        Item item;

        public ArrayList<Item> getItemsList() {
            return itemsList;
        }
        static public ArrayList<Item> newMethod(InputStream in) throws IOException, SAXException {
            NewClass parser= new NewClass();
            Xml.parse(in,Xml.Encoding.UTF_8,parser);
            return parser.getItemsList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            sb= new StringBuilder();
            itemsList= new ArrayList<Item>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("item")){
                item= new Item();

            }else if(localName.equals("image")&&item!=null){
                item.setImageurl(attributes.getValue("href"));

            }else if(localName.equals("enclosure")&&item!=null){
                item.setMp3url(attributes.getValue("url"));

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("item")){
                itemsList.add(item);
            }else if(localName.equals("title")&& item!=null){
                item.setTitle(sb.toString().trim());

            }else if(localName.equals("description")&&item!=null){
                item.setDescription(sb.toString().trim());
            }else if(localName.equals("duration")&&item!=null){
                String s= sb.toString().trim();
                int i=Integer.parseInt(s);
                item.setDuration(i);

            }else if(localName.equals("pubDate")&&item!=null){
                String date=sb.toString().trim();
                SimpleDateFormat sdf=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZZ");
                try {
                    Date d=sdf.parse(date);
                    item.setDate(d);
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
