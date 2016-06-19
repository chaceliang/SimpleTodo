package com.chaceliang.simpletodo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by chaceliang on 6/18/16.
 */
public class LocalStorage {

  public static ArrayList<String> readItems(File filesDir) {
    File todoFile = new File(filesDir, "todo.txt");
    ArrayList<String> items;
    try {
      items = new ArrayList<>(FileUtils.readLines(todoFile));
    } catch (IOException e) {
      items = new ArrayList<>();
    }
    return items;
  }

  public static void writeItems(File filesDir, ArrayList<String> items) {
    File todoFile = new File(filesDir, "todo.txt");

    try {
      FileUtils.writeLines(todoFile, items);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
