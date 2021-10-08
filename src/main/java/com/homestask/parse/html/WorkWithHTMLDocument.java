package com.homestask.parse.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.util.*;

public class WorkWithHTMLDocument {
    private Document doc;

    public WorkWithHTMLDocument(String URL, String userAgent, String referrer) {
        try {
            this.doc = Jsoup.connect(URL)
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Element getSelect(String jQuery) {
        Element div = doc.select("div").first();

        return div;
    }

    public String stripTags(StringBuilder builder, List<Node> nodesList) {
        for (Node node : nodesList) {
            String nodeName = node.nodeName();

            if (nodeName.equalsIgnoreCase("#text")) {
                builder.append(node.toString());
            } else {
                stripTags(builder, node.childNodes());
            }
        }

        return builder.toString();
    }

    public HashMap<String, Integer> uniqueWord(String builder) {
        HashMap<String, Integer> wordToCount = new HashMap<>();
        String[] words = builder
                .replaceAll("[^\nа-яёА-ЯЁa-zA-Z ]", "")
                .split(" ");

        fillUpMap(words,wordToCount);
        replaceKeysChar("\n","",wordToCount);
        removeKeysChar(wordToCount);

        return wordToCount;
    }

    private HashMap<String, Integer> fillUpMap(String[] words,HashMap<String, Integer> map){
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
        }

        return map;
    }

    private void removeKeysChar(Map<String, Integer> map){
        Iterator<Map.Entry<String,Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String,Integer> entry = iter.next();
            if("".equalsIgnoreCase(entry.getKey())){
                iter.remove();
            }
        }
    }

    private void replaceKeysChar(String originalChar, String newChar, Map<String, Integer> map) {
        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if(key != null){
                key = key.replace(originalChar, newChar);
            }
            tempMap.put(key, (Integer) entry.getValue());
        }
        map.clear();
        map.putAll(tempMap);
    }
}