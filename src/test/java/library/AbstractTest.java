package library;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractTest {
	
	@Value("${server.success.httpstatus}")
	private int successfulHttpCode;
	@Value("${server.created.httpstatus}")
	private int createdHttpCode;
	@Value("${server.badrequest.httpstatus}")
	private int badRequestHttpCode;
	@Value("${server.not.found.httpstatus}")
	private int notFoundHttpCode;
	@Value("${server.error.httpstatus}")
	private int serverErrorHttpCode;
	
	private final static String SUCCESS_TEST = "STATUS: ####    Success    #####";
	private final static String FAIL_TEST = "STATUS: ####    Fail    #####";
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

	public int getSuccessfulHttpCode() {
		return successfulHttpCode;
	}

	public void setSuccessfulHttpCode(int successfulHttpCode) {
		this.successfulHttpCode = successfulHttpCode;
	}

	public int getCreatedHttpCode() {
		return createdHttpCode;
	}

	public void setCreatedHttpCode(int createdHttpCode) {
		this.createdHttpCode = createdHttpCode;
	}

	public int getBadRequestHttpCode() {
		return badRequestHttpCode;
	}

	public void setBadRequestHttpCode(int badRequestHttpCode) {
		this.badRequestHttpCode = badRequestHttpCode;
	}

	public int getNotFoundHttpCode() {
		return notFoundHttpCode;
	}

	public void setNotFoundHttpCode(int notFoundHttpCode) {
		this.notFoundHttpCode = notFoundHttpCode;
	}

	public int getServerErrorHttpCode() {
		return serverErrorHttpCode;
	}

	public void setServerErrorHttpCode(int serverErrorHttpCode) {
		this.serverErrorHttpCode = serverErrorHttpCode;
	}

	public String getUrl() {
		return url;
	}

	public static String getSuccessTest() {
		return SUCCESS_TEST;
	}

	public static String getFailTest() {
		return FAIL_TEST;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	
}
