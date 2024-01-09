package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngm;
    public HistoryTextHandler(NGramMap map){
        ngm = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String response = "";
        for (String word : words) {
            response += word + ": " + ngm.weightHistory(word, q.startYear(), q.endYear()).toString() + "\n";
        }
        return response;
    }
}
