package library;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Fabrizio Sosa
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractTest {
	
	private final static String SUCCESS_TEST = "STATUS: ####    Success    #####";
	private final static String url = "/library";
	
	/**
	 * It allows to transform objects from a given class to JSON.
	 * 
	 */
	@Autowired
	private ObjectMapper mapper;
	
	protected MockMvc mvc;

	@Autowired
	WebApplicationContext wap;
	
	protected void setup () {
		this.mvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}
	
	/*###	Getters & setters	###*/

	public String getUrl() {
		return url;
	}

	public static String getSuccessTest() {
		return SUCCESS_TEST;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	
}
