package sample.model.dictionary;

import java.util.*;
import java.util.regex.*;

/**
 * Created by VAUst on 26.10.2018.
 */
public class Dictionary {
    private Map<Integer, Map<String, Integer>> words;
    private List<Word> wordsList;
    private static volatile Dictionary instance;

    private Dictionary() {
        words = new HashMap<Integer, Map<String, Integer>>();
        for(int index = 2; index < 25; index++) {
            words.put(index, new HashMap<String, Integer>());
        }
        wordsList = new ArrayList<Word>(0);
    }

    private String convertTemplate(String template) {
        String word = template.toUpperCase().trim();
        StringBuilder buf = new StringBuilder();
        for(int index = 0; index < word.length(); index++) {
            if(template.charAt(index) == '?') {
                buf.append('.');
            }
            else {
                buf.append(word.charAt(index));
            }
        }
        return buf.toString();
    }

    public static Dictionary getInstance() {
        Dictionary localInstance = instance;
        if(localInstance == null) {
            synchronized (Dictionary.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Dictionary();
                }
            }
        }
        return localInstance;
    }

    public Word getWord(String wordValue) {
        if(words.get(wordValue.length()).containsKey(wordValue)) {
            return wordsList.get(words.get(wordValue.length()).get(wordValue));
        } else {
            return null;
        }
    }

    public void addWord(Word word) {
        if (!wordsList.contains(word)) {
            List<Word> tmp = new ArrayList<Word> (wordsList.size() + 1);
            tmp.addAll(wordsList);
            tmp.add(word);
            wordsList = tmp;
            Map<String, Integer> iMap = words.get(word.getValue().length());
            iMap.put(word.getValue(), tmp.size()-1);
            words.put(word.getValue().length(), iMap);
        }
    }

    public boolean containsWord(Word word) {
        return wordsList.contains(word);
    }

    public boolean containsWord(String wordValue) {
        return words.get(wordValue.length()).containsKey(wordValue);
    }

    public void removeWord(Word word) {
        wordsList.remove(word);
        words.get(word.getValue().length()).remove(word.getValue());
    }

    public void removeWord(String wordValue) {
        wordsList.remove(wordsList.get(words.get(wordValue.length()).get(wordValue)));
        words.get(wordValue.length()).remove(wordValue);
    }

    public List<String> getWordsByLength(int length) {
        return new ArrayList<String>(words.get(Integer.valueOf(length)).keySet());
    }

    public List<String> getWordsByPatternAndCategory(String template, String categoryValue) {
        List<String> list = new LinkedList<String> ();
        Pattern pattern = Pattern.compile(convertTemplate(template));

        for(String word: getWordsByCategory(categoryValue)) {
            Matcher matcher = pattern.matcher(word);
            if(matcher.matches()) {
                list.add(word);
            }
        }
        return list;
    }

    public List<String> getWordsByPattern(String template) {
        List<String> list = new LinkedList<String>();

        Pattern pattern = Pattern.compile(convertTemplate(template));

        for(Map<String, Integer> element: words.values()) {
            for(String word: element.keySet()) {
                Matcher matcher = pattern.matcher(word);
                if(matcher.matches()) {
                    list.add(word);
                }
            }
        }
        return list;
    }

    public List<String> getWordsByCategory(String categoryValue) {
        List<String> list = new LinkedList<String> ();
        for(Word element: wordsList) {
            if(element.containCategory(categoryValue)) {
                list.add(element.getValue());
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Number of words in dictionary: ");
        info.append(wordsList.size());
        return info.toString();
    }
}
