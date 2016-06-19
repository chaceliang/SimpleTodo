package com.chaceliang.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_item);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Intent i = getIntent();

    EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
    etEditItem.setText(i.getStringExtra("editItem"));
  }

  public void onSaveItem(View v) {
    EditText etEditItem = (EditText) findViewById(R.id.etEditItem);

    String itemText = etEditItem.getText().toString();

    Intent data = new Intent();
    data.putExtra("newItem", itemText);

    setResult(RESULT_OK, data);
    finish();
  }

}
