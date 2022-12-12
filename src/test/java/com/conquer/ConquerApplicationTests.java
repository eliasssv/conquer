package com.conquer;

import com.conquer.service.ReportCityExpensesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConquerApplicationTests {

	@InjectMocks
	private ReportCityExpensesServiceImpl reportCityExpensesService;

	@Test
	void testFormatValue() {
		Double result =  reportCityExpensesService.formatValue("1.000,23");
		Assertions.assertEquals(1000.23, result);
	}

	@Test
	void testFormatValueError() {
		Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> reportCityExpensesService.formatValue("ABC,123"));

		String expectedMessage = "For input string";
		String actualMessage = exception.getMessage();

		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

}
