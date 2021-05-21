package com.sms.android.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

import com.sms.android.bean.PhoneBook;


public class GetContractInfo {

	private  Context mContext = null;

//	public void TestRead(){
//
//		HashMap<String, Phonebook> map = this.getPhoneContracts();
//
//		Iterator iterator = map.keySet().iterator();
//
//		while ( iterator.hasNext()) {
//			Object key = iterator.next();
//			System.out.println("Name: " + map.get(key).GetName());
//			System.out.println("PhoneNumber: " + map.get(key).GetPhoneNumber());
//		}
//	}

	public List<PhoneBook> getPhonebookList(){

		List<PhoneBook> list=new ArrayList<>();
		HashMap<String, PhoneBook> map = this.getPhoneContracts();

		Iterator iterator = map.keySet().iterator();

		while ( iterator.hasNext()) {
			Object key = iterator.next();
			list.add(map.get(key));
		}
		return list;
	}

	public GetContractInfo(  Context Context ){

		this.mContext = Context;
	}


	//取本机通讯录
	public HashMap<String, PhoneBook> getPhoneContracts(){

		HashMap<String, PhoneBook> map = new HashMap<String, PhoneBook>();
		ContentResolver resolver = mContext.getContentResolver();
		// 获取手机联系人

		Cursor phoneCursor = null;

		try{

			phoneCursor = resolver.query(Phone.CONTENT_URI,null, null, null, null); //传入正确的uri

		}catch ( SecurityException e){

			e.printStackTrace();
		}


		if(phoneCursor!=null){

			while(phoneCursor.moveToNext()){
				int nameIndex = phoneCursor.getColumnIndex(Phone.DISPLAY_NAME); //获取联系人name
				String name = phoneCursor.getString(nameIndex);
				String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER)); //获取联系人number

				if(TextUtils.isEmpty(phoneNumber)){
					continue;
				}

				PhoneBook contractInfo = new PhoneBook();
				contractInfo.setUsername( name);
				contractInfo.setPhoneNumber( phoneNumber );

				map.put( phoneNumber , contractInfo);
			}
			phoneCursor.close();
		}

		return map;
	}


	public HashMap<String, PhoneBook> getSimContracts(){
		//读取SIM卡手机号,有两种可能:content://icc/adn与content://sim/adn
		HashMap<String, PhoneBook> map = new HashMap<String, PhoneBook>();

		ContentResolver resolver = mContext.getContentResolver();
		Uri uri = Uri.parse("content://icc/adn");
		Cursor phoneCursor = resolver.query(uri,null, null, null, null);
		if(phoneCursor!=null){

			while(phoneCursor.moveToNext()){

				String name = phoneCursor.getString(phoneCursor.getColumnIndex("name"));
				String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex("number"));

				if(TextUtils.isEmpty(phoneNumber)){
					continue;
				}
				//以下是我自己的数据封装。
				PhoneBook contractInfo = new PhoneBook();
				contractInfo.setUsername(name);
				contractInfo.setPhoneNumber( phoneNumber );

				map.put( phoneNumber , contractInfo);
			}
			phoneCursor.close();
		}
		return map;
	}


}
