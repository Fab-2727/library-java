package library;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
class ControllerJTest extends AbstractTest {

	
	@Override
	@BeforeEach
	protected void setup() {
		super.setup();
	}
	
	
	@Test
	public void getAllBooksTest() {
		try {
			mvc.perform(get("/library/book/all")).andReturn());

		} catch (Exception e) {
			System.out.println("In try-catch TEST method");
			e.printStackTrace();
		}
		
	}
	
	
}
