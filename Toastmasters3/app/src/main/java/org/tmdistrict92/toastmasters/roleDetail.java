package org.tmdistrict92.toastmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class roleDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_detail);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra(MainActivity.roleTitleintent));
        TextView textViewForRoleDetailDisplay = (TextView) findViewById(R.id.textViewForRoleDetailDisplay);
        textViewForRoleDetailDisplay.setMovementMethod(new ScrollingMovementMethod());
        textViewForRoleDetailDisplay.setText(intent.getStringExtra(MainActivity.roleDataintent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_role_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
