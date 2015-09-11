//package com.district92.toastmasters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.ListAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Karthik R Rao on 9/11/15.
// */
//public class ClubListAdapter extends BaseAdapter {
//
//    List<String> mList;
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getCount() {
//        return mList.size();
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null) {
//            LayoutInflater inflater =
//            ViewHolder holder = new ViewHolder();
//            holder.textView1 = (TextView) convertView.findViewById(R.id.club_list_item_textView1);
//            holder.textView2 = (TextView) convertView.findViewById(R.id.club_list_item_textView2);
//            convertView.setTag(holder);
//        }
//        return null;
//    }
//
//    public ClubListAdapter(Context context, List<String> list) {
//
//    }
//
//    static class ViewHolder {
//        TextView textView1;
//        TextView textView2;
//        int position;
//    }
//
//}