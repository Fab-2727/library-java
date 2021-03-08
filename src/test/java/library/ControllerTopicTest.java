package library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNot;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;


//Given (Input): Test preparation like creating data or configure mocks
//When (Action): Call the method or action that you like to test
//Then (Output): Execute assertions to verify the correct output or behavior of the action.

/**
 * This class test the controller methods that use the entity {@linkplain Topic}
 * 
 * @author fab
 *
 */
@TestPropertySource(locations = "classpath:application-test.properties")
public class ControllerTopicTest extends AbstractTest {

	private final String urlTopic = getUrl()+"/topic";
	
	@Override
	@BeforeEach
	protected void setup() {
		super.setup();
	}

	/*		Get all topics 		*/
	
	@Test
	public void testGetAllTopics() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			ResultActions rstAction = mvc.perform(get(urlTopic+"/all"));
			rstAction.andExpect(status().isOk());
			assertNotEquals(null, rstAction.andReturn().getResponse().getContentAsString());
			assertNotEquals("", rstAction.andReturn().getResponse().getContentAsString());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		Get one topic by ID		*/
	/**
	 * Id: 1 (one)
	 */
	@Test
	public void testGetOneTopicById () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(urlTopic).queryParam("id", "1"))
			.andExpect(content() .string(containsString("\"id\":")))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		Get one topic by NAME		*/
	
	/**
	 * Using topic name 'Drama'
	 */
	@Test
	public void testGetOneTopicByName () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(urlTopic+"/param").queryParam("name", "Drama"))
			.andExpect(content() .string(containsString("\"id\":")))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		Add new topic		*/

	@Test
	public void testAddNewTopicSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			JSONObject requestContent = new JSONObject();
			requestContent.put("topicName", "Test add-topic");
			
			mvc.perform(post(urlTopic+"/add-topic").content(requestContent.toString()))
			.andExpect(status().isCreated());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
