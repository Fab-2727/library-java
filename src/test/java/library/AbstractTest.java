package library;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext wap;
	
	protected void setup () {
		this.mvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}
	
}
