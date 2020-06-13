package com.kkbs.orderbrushing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest
class OrderBrushingApplicationTests {

	String[] HEADERS = { "orderid", "shopid", "userid", "event_time"};

	public String oneHourLater(String oldTime) {
		String hh = oldTime.substring(11,13);
		if (hh.equals("23")) {
			hh = "00";
		} else {
			int h = Integer.parseInt(hh);
			hh = h < 9 ? "0" + (h + 1) : String.valueOf(h + 1);
		}
		return oldTime.substring(0, 11) + hh + oldTime.substring(13);
	}

	@Test
	public void shouldReadFile() throws IOException {
		Reader in = new FileReader("src/main/resources/order_brush_order.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT
			.withHeader(HEADERS)
			.withFirstRecordAsHeader()
			.parse(in);
		ArrayList<Order> orderList = new ArrayList<>();
		for (CSVRecord record : records) {
			long orderId = Long.parseLong(record.get("orderid"));
			long shopId = Long.parseLong(record.get("shopid"));
			long userId = Long.parseLong(record.get("userid"));
			String eventTime = record.get("event_time");

			if (shopId == 145777302) {
				Order order = new Order(orderId, shopId, userId, eventTime);
				orderList.add(order);
			}
		}

		Collections.sort(orderList);
		ArrayList<Long> brushers = new ArrayList<>();
		for (int i = 0; i < orderList.size(); i++) {
			Order order = orderList.get(i);
			int j = 1;
			while ( i + j < orderList.size() && orderList.get(i+j).getEventTime().compareTo(oneHourLater(order.getEventTime())) < 0) {
				if (order.getUserId() == orderList.get(i+j).getUserId()) {
					if (!brushers.contains(order.getUserId())) {
						brushers.add(order.getUserId());
					}
				}
			j++;
			}
		}
		System.out.println(brushers.toString());
	}

}
