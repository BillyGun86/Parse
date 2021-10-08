package com.homestask.parse;

import com.homestask.parse.db.DBase;
import com.homestask.parse.db.DBaseImpl;
import com.homestask.parse.entity.Text;
import com.homestask.parse.html.WorkWithHTMLDocument;
import org.hibernate.Session;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        WorkWithHTMLDocument doc = new WorkWithHTMLDocument(
                "https://www.simbirsoft.com/",
                "Chrome/4.0.249.0 Safari/532.5",
                "https://www.google.com/");

        Element div = doc.getSelect("div");
        StringBuilder builder = new StringBuilder();
        String str = doc.stripTags(builder, div.childNodes());
        HashMap<String, Integer> unique = doc.uniqueWord(str);

        DBase dBase = new DBaseImpl();
        try {
            for (Map.Entry<String, Integer> entry : unique.entrySet()) {
                Text text = new Text();
                text.setWord(entry.getKey());
                text.setCount(entry.getValue());

                Session session = dBase.getCurrentSession();
                session.beginTransaction();
                session.save(text);
                session.getTransaction().commit();
            }
        } finally {
            dBase.closeCurrentSession();
        }
    }
}