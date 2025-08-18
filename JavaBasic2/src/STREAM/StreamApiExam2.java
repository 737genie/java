package STREAM;

import java.util.*;
import java.util.stream.Collectors;

class Order {
    String category;
    double price;
    public Order(String c, double p) { category = c; price = p; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}

public class StreamApiExam2 {

	// 바쁘개의 월말 정산
	// 한달치 판매기록이 있는데 이걸 메뉴 카테고리별 그룹을 지어서
	// 각 카테고리의 총 매출액을 계산한 결과로 만들어줘!
	public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("음료", 2.0), new Order("디저트", 3.0),
                new Order("음료", 4.5), new Order("디저트", 5.5),
                new Order("음료", 2.0)
            );
        
        Map<String, Double> sales = orders.stream()
        		.collect(Collectors.groupingBy(
        				Order::getCategory,
        				Collectors.summingDouble(Order::getPrice) //합계를 double 타입으로 리턴, 
        				));
        System.out.println("바쁘개 : 월말 매출 결과입니다!");
        System.out.println(sales);
        
        
	}
	
}