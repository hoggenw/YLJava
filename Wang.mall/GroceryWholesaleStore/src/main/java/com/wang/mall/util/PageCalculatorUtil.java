package com.wang.mall.util;

public class PageCalculatorUtil {
	public static int calculatorRowIndex(int pageIndex, int pagesize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pagesize : 0;
	}

}
