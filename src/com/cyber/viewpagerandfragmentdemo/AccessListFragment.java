package com.cyber.viewpagerandfragmentdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AccessListFragment extends Fragment implements OnItemClickListener {
	
	private ListView listView;
	private AccessListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_access_list, container, false);
		
		adapter = new AccessListAdapter(getItems());
		listView = (ListView) view.findViewById(R.id.lv_access_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		return view;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(), "123213", Toast.LENGTH_SHORT).show();
	}

	// 测试数据，稍后修改为从服务器获取
	private List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		String[] strings = {"zhangsan","lisi","wangwu","zhaoliu"};
		for(String s : strings) {
			Item item = new Item();
			item.nickname = s;
			items.add(item);
		}
		return items;
	}
	
	public class AccessListAdapter extends BaseAdapter {
		private List<Item> list;
		
		public AccessListAdapter(List<Item> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Item getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			ItemHolder holder = null;
			if(view == null) {
				view = LayoutInflater.from(getActivity()).inflate(R.layout.lv_access_list, parent);
				
				holder = new ItemHolder();
				holder.listItem = (TextView) view.findViewById(R.id.lv_access_list_item);
				
				view.setTag(holder);
			} else {
				holder = (ItemHolder) view.getTag();
			}
			
			Item item = list.get(position);
			holder.listItem.setText(item.nickname);
			
			return view;
		}
		
	}
	
	class Item {
		String nickname;
	}
	
	class ItemHolder {
		TextView listItem;
	}

}
