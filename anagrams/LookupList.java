package anagrams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LookupList {
  private ArrayList<String> wordlist;

  public LookupList() {
    wordlist = new ArrayList<String>();
  }

  public LookupList(String fileName) throws FileNotFoundException {
    wordlist = new ArrayList<String>();
    Scanner file = new Scanner(new FileReader(fileName));
    while (file.hasNextLine()) {
      String word = file.nextLine();
      if (word.length() == 0 || word.charAt(0) == '#') // skip blank lines or starting with #, treating them as comments
        continue;
      wordlist.add(word);
      /*
       * // Track how quickly the wordlist is forming...
       * if (wordlist.size() % 5000 == 0)
       * System.out.println("Added " + numWords + " words.");
       */
    }
    file.close();

    mergeSort();
    // TODO update to use merge sort!
  }

  

  private void mergeSort() {
    mergeSort(wordlist);
  }

  private ArrayList<String> mergeSort(ArrayList<String> list) {
    if (list.size() <= 1) {
      return list;
    }

    ArrayList<String> left = new ArrayList<>(list.subList(0, list.size() / 2));
    ArrayList<String> right = new ArrayList<>(list.subList(list.size() / 2, list.size()));

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left, right);
  }

  

  private ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
    ArrayList<String> merged = new ArrayList<>(); 
    int lIndex = 0;
    int rIndex = 0;

    while(lIndex < left.size() && rIndex < right.size()){
      if(left.get(lIndex).compareTo(right.get(rIndex)) < 0){
        merged.add(left.get(lIndex++));
      }
      else{
        merged.add(right.get(rIndex++));
      }
    }

    //leftovers

    while(lIndex < left.size()){
      merged.add(left.get(lIndex++));
    }
    while(rIndex < right.size()){
      merged.add(right.get(rIndex++));
    }

    return merged;
  }

  public int size() {
    return wordlist.size();
  }

  public String get(int index) {
    return wordlist.get(index);
  }

  public int indexOf(String word) {
    return wordlist.indexOf(word);

    // TODO update to use binary search!
  }

  public boolean contains(String word) {
    return wordlist.contains(word);

    // TODO update to use binary search!
  }

  public List<String> toList() {
    return wordlist;
  }

  public void print() {
    int maxdigits = String.valueOf(wordlist.size()).length();
    ;
    String format = "%" + maxdigits + "d: %s\n";

    System.out.printf("Wordlist (%d total):\n", wordlist.size());
    for (int i = 0; i < wordlist.size(); i++) {
      System.out.printf(format, i, wordlist.get(i));
    }
  }

  public void insert(String word) {
    // This inserts a word into the list in sorted position,
    // assuming that the list is already sorted.

    for (int i = wordlist.size(); i > 0; i--) { // work backwards
      String w = wordlist.get(i - 1);
      if (word.compareTo(w) >= 0) { // if >= item in list (w),
        wordlist.add(i, word); // insert at correct place, which is right after the item
        return; // done!
      }
    }
    wordlist.add(0, word); // if < all items in list, add to front of list

    // TODO Optional?? Rewrite to work a little bit faster! Hint: Use binarySearch
    // to know WHERE to insert...
  }

}
