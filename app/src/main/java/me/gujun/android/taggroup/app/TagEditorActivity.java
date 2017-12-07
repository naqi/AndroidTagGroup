package me.gujun.android.taggroup.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.gujun.android.taggroup.AndroidTagGroup;
import me.gujun.android.taggroup.app.db.TagsManager;


public class TagEditorActivity extends AppCompatActivity {

    private static final String TAG = "TagEditorActivity";

    private AndroidTagGroup mAndroidTagGroup;
    private TagsManager mTagsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_editor);

        mTagsManager = TagsManager.getInstance(getApplicationContext());
        String[] tags = mTagsManager.getTags();

        mAndroidTagGroup = findViewById(R.id.androidTagGroup);
        mAndroidTagGroup.setOnInputTagFocusChangedListener(new AndroidTagGroup.OnInputFocusChangedListener() {
            @Override
            public void onFocusChange(View tagView, boolean hasFocus) {
                Log.d(TAG, "Input tag has focus: " + hasFocus);
            }
        });
        mAndroidTagGroup.setTags(tags, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tag_editor_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mTagsManager.updateTags(mAndroidTagGroup.getTags());
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_submit) {
            mAndroidTagGroup.submitTag();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        mTagsManager.updateTags(mAndroidTagGroup.getTags());
        super.onBackPressed();
    }
}