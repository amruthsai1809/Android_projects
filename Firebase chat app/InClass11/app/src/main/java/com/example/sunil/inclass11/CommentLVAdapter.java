package com.example.sunil.inclass11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;

/**
 * Created by sunil and amruth on 4/10/17.
 */

public class CommentLVAdapter extends ArrayAdapter<Chat.Comments> {

    int resource;
    Context context;
    List<Chat.Comments> objects;


    public CommentLVAdapter(Context context, int resource, List<Chat.Comments> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resource, parent, false);
        }

        Chat.Comments comments = objects.get(position);

        ((TextView) convertView.findViewById(R.id.commentText420)).setText(comments.comment);
        ((TextView) convertView.findViewById(R.id.commentUserName420)).setText(comments.userName);
        ((TextView) convertView.findViewById(R.id.commentedTime567)).setText(new PrettyTime().format(comments.addedTime));

        return convertView;
    }
}