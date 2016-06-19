package com.chaceliang.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  ArrayList<String> items;
  ArrayAdapter<String> itemsAdapter;
  ListView lvItems;

  private final int REQUEST_CODE = 20;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    items = LocalStorage.readItems(getFilesDir());
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lvItems = (ListView) findViewById(R.id.lvItems);
    itemsAdapter = new ArrayAdapter<String>(
        this,
        android.R.layout.simple_list_item_1,
        items);
    lvItems.setAdapter(itemsAdapter);
    setupListViewListener();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      String item = data.getExtras().getString("newItem");
      items.remove(requestCode);
      items.add(item);
      itemsAdapter.notifyDataSetChanged();
      LocalStorage.writeItems(getFilesDir(), items);
    }
  }

  public void onAddItem(View v) {
    EditText etNewItem = (EditText) findViewById(R.id.etNewItem);

    String itemText = etNewItem.getText().toString();
    items.add(itemText);
    etNewItem.setText("");
    LocalStorage.writeItems(getFilesDir(), items);
  }

  private void setupListViewListener() {
    lvItems.setOnItemLongClickListener(
        new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            LocalStorage.writeItems(getFilesDir(), items);
            return true;
          }
        }
    );

    lvItems.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = ((TextView)view).getText().toString();
            Intent intent = new Intent(getApplicationContext(), EditItemActivity.class);

            intent.putExtra("editItem", selectedItem);
            startActivityForResult(intent, position);
          }
        }
    );
  }
}
