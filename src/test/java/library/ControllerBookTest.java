package library;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;

@TestPropertySource(locations = "classpath:application-test.properties")
class ControllerJTest extends AbstractTest {

	
	@Override
	@BeforeEach
	protected void setup() {
		super.setup();
	}
	
	
	@Test
	public void getAllBooksTest() {
		try {
			MvcResult rst = mvc.perform(get("/library/book/all")).andReturn();
			System.out.println("STATUS: " + rst.getResponse().getStatus());
			System.out.println("BODY: " + rst.getResponse().getContentAsString());
			
		} catch (Exception e) {
			System.out.println("In try-catch TEST method");
			e.printStackTrace();
		}
		
	}
	
	
}
