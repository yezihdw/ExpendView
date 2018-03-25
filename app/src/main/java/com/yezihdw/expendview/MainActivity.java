package com.yezihdw.expendview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import static com.yezihdw.expendview.MultiTypeExpandAdapter.TYPE_EDITTEXT;
import static com.yezihdw.expendview.MultiTypeExpandAdapter.TYPE_IMAGE;
import static com.yezihdw.expendview.MultiTypeExpandAdapter.TYPE_TEXT;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener {

    private ExpandableListView mListView;
    private MultiTypeExpandAdapter mAdapter;

    private List<GroupModel> groupModels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.expand_listview);


        getGroupModelList();
        mAdapter = new MultiTypeExpandAdapter(MainActivity.this, groupModels);

        mListView.setAdapter(mAdapter);

        mListView.setOnGroupClickListener(this);
    }

    private void getGroupModelList() {
        groupModels = new ArrayList<>();
        groupModels.clear();

        GroupModel groupModel1 = new GroupModel();
        groupModel1.setName("group1");
        groupModel1.setType(TYPE_TEXT);
        List<ChildModel> childModels1 = getChildModelList(5);
        groupModel1.setChildModels(childModels1);
        groupModels.add(groupModel1);

        GroupModel groupModel2 = new GroupModel();
        groupModel2.setName("group2");
        groupModel2.setType(TYPE_IMAGE);
        groupModels.add(groupModel2);

        GroupModel groupModel3 = new GroupModel();
        groupModel3.setName("group3");
        groupModel3.setType(TYPE_TEXT);

        List<ChildModel> childModels2 = getChildModelList(7);
        groupModel3.setChildModels(childModels2);
        groupModels.add(groupModel3);


        GroupModel groupModel4 = new GroupModel();
        groupModel4.setName("group4");
        groupModel4.setType(TYPE_TEXT);
        groupModels.add(groupModel4);

        GroupModel groupModel5 = new GroupModel();
        groupModel5.setName("group5");
        groupModel5.setType(TYPE_EDITTEXT);
        groupModels.add(groupModel5);

    }

    private List<ChildModel> getChildModelList(int length) {
        List<ChildModel> childModels = new ArrayList<>();
        for (int i=0; i<length; i++) {
            ChildModel childModel = new ChildModel();
            childModel.setName("child"+i);

            childModels.add(childModel);
        }

        return childModels;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }
}
