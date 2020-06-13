package com.kkbs.orderbrushing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@SpringBootTest
class OrderBrushingApplicationTests {

	String[] HEADERS = { "orderid", "shopid", "userid", "event_time"};

	@Test
	public void shouldReadFile() throws IOException {
		Reader in = new FileReader("src/main/resources/order_brush_order.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT
			.withHeader(HEADERS)
			.withFirstRecordAsHeader()
			.parse(in);
		for (CSVRecord record : records) {
			long orderId = Long.parseLong(record.get("orderid"));
			long shopId = Long.parseLong(record.get("shopid"));
			long userId = Long.parseLong(record.get("userid"));
			String eventTime = record.get("event_time");

			if (shopId == 93950878) {
				Order order = new Order(orderId, shopId, userId, eventTime);
				System.out.println(order.toString());
			}
		}
	}

}
