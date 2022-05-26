package com.jingbabyadmin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IconfontUtils {

	/**
	 * 获取所有图标
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public static List<String> getIconfonts(HttpServletRequest request) throws IOException{
		List<String> icons = new ArrayList<String>();
		String path = request.getRequestURL().toString().replace(request.getRequestURI().toString(), "") + request.getContextPath() + "/static/iconfont/demo_fontclass.html";
		Response response = Jsoup.connect(path).execute();
		Document document = Jsoup.parse(response.body());
		Elements elements = document.getElementsByClass("fontclass");
		for (Element e : elements) {
			icons.add(e.text().replace(".", ""));
		}
		return icons;
	}
}
