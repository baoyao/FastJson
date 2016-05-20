
包含
fastjson-1.2.5.jar
gson-2.2.4.jar
volley.jar(访问网络)

 




package test_json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class TestJson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long startTime=System.currentTimeMillis();
		
		List<BookStore> superList = new ArrayList<BookStore>();

		for (int i = 0; i < 3; i++) {
			BookStore bStore=new BookStore();
			
			List<Book> sublist = new ArrayList<Book>();
			for (int j = 0; j < 1000000; j++) {
				Book book = new Book();
				book.setId("id_" + i + j);
				book.setCatalog("log_" + i + j);
				sublist.add(book);
			}
			bStore.setStoreId("sId_"+i);
			bStore.setBookList(sublist);
			superList.add(bStore);
		}
		
		startTime=System.currentTimeMillis();
		String json = JSONArray.toJSONString(superList);//to json
		System.out.println("use time1: " + (System.currentTimeMillis()-startTime));
		//100W used 2380 second
		//200W used 3781 second
		
//		System.out.println("json1: " + json);
		
		

		startTime=System.currentTimeMillis();
		List<BookStore> resultList=JSON.parseArray(json, BookStore.class);//parse json
		System.out.println("use time2: " + (System.currentTimeMillis()-startTime));
		//100W used 1458 second
		//200W used 1517 second
		
//		System.out.println("resultList: " + resultList);
		
		
//		for (int i = 0; i < resultList.size(); i++) {
//			System.out.println("getStoreId: " + resultList.get(i).getStoreId());
//			for (int j = 0; j < resultList.get(i).getBookList().size(); j++) {
//				System.out.println("getId: " + resultList.get(i).getBookList().get(j).getId());
//				System.out.println("getCatalog: " + resultList.get(i).getBookList().get(j).getCatalog());
//				
//			}
//		}
		
	}

}

