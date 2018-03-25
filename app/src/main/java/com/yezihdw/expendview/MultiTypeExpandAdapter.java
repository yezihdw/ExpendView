package com.yezihdw.expendview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yeziwen on 2018/3/25.
 */

public class MultiTypeExpandAdapter extends BaseExpandableListAdapter {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_EDITTEXT = 2;

    private static final int TYPE_COUNT = 3;

    private List<GroupModel> groupModels = null;

    private Context context;
    private LayoutInflater inflater;

    public MultiTypeExpandAdapter(Context context, List<GroupModel> groupModels) {
        this.context = context;
        this.groupModels = groupModels;
    }

    @Override
    public int getGroupType(int groupPosition) {

        return groupModels.get(groupPosition).getType();
    }

    @Override
    public int getGroupTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getGroupCount() {
        return groupModels.size()>0 ? groupModels.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupModels.get(groupPosition).getChildModels() != null ?
                    groupModels.get(groupPosition).getChildModels().size() :
                    0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupModels.get(groupPosition)!=null ? groupModels.get(groupPosition) : null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupModels.get(groupPosition).getChildModels().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        int type = getGroupType(groupPosition);
        switch(type) {
            case TYPE_TEXT:
                return handleTextView(groupPosition, isExpanded, convertView, parent);
            case TYPE_IMAGE:
                return handleImageView(groupPosition, isExpanded, convertView, parent);
            case TYPE_EDITTEXT:
                return handleEditText(groupPosition, isExpanded, convertView, parent);
            default:
                return null;
        }
    }

    private View handleTextView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.text_item, parent, false);

            holder = new ViewHolder1();
            holder.textTv = convertView.findViewById(R.id.textView);
            holder.valueTv = convertView.findViewById(R.id.textView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder1) convertView.getTag();
        }
        GroupModel groupModel = groupModels.get(groupPosition);
        holder.textTv.setText(groupModel.getName());
        holder.valueTv.setText(groupModel.getTextValue());
        return convertView;
    }

    private View handleImageView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder2 holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);

            holder = new ViewHolder2();
            holder.textTv = convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder2) convertView.getTag();
        }
        GroupModel groupModel = groupModels.get(groupPosition);
        holder.textTv.setText(groupModel.getName());
        return convertView;
    }

    private View handleEditText(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder3 holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.edittext_item, parent, false);

            holder = new ViewHolder3();
            holder.textTv = convertView.findViewById(R.id.textView);
            holder.valueTv = convertView.findViewById(R.id.textView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder3) convertView.getTag();
        }
        GroupModel groupModel = groupModels.get(groupPosition);
        holder.textTv.setText(groupModel.getName());
        return convertView;
    }

    static class ViewHolder1{
        TextView textTv;
        TextView valueTv;
    }

    static class ViewHolder2{
        TextView textTv;
    }

    static class ViewHolder3{
        TextView textTv;
        TextView valueTv;
    }

    static class ChildViewHolder{
        TextView textTv;
        CheckBox checkBox;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);

            holder = new ChildViewHolder();
            holder.textTv = convertView.findViewById(R.id.textView);
            holder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        GroupModel groupModel = groupModels.get(groupPosition);
        ChildModel childModel = groupModel.getChildModels().get(childPosition);
        holder.textTv.setText(childModel.getName());
        holder.checkBox.setChecked(childModel.isChecked());

        holder.checkBox.setTag(R.drawable.ic_launcher_background, groupPosition);
        holder.checkBox.setTag(R.drawable.ic_launcher_foreground, childPosition);
        holder.checkBox.setOnClickListener(onClickListener);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int groupPos = (int) v.getTag(R.drawable.ic_launcher_background);
            int childPos = (int) v.getTag(R.drawable.ic_launcher_foreground);
            setSelChildModel(groupPos, childPos);

        }
    };

    private void setSelChildModel(int groupPos, int childPos) {
        GroupModel groupModel = groupModels.get(groupPos);
        List<ChildModel> childModels = groupModel.getChildModels();

        for (int i=0; i< childModels.size(); i++) {
            ChildModel childModel = childModels.get(i);
            if (childPos == i) {
                childModel.setChecked(true);
            } else {
                childModel.setChecked(false);
            }
        }
        notifyDataSetChanged();
    }
}
