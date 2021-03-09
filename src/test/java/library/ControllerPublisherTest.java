package library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;

import library.model.Publisher;

/**
 * This class test the controller methods that use the entity {@linkplain Topic}
 * 
 * @author fab
 * mvn clean test -Dtest=ControllerPublisherTest#testAddNewPublisher

 */
public class ControllerPublisherTest extends AbstractTest{

	@BeforeEach
	@Override
	protected void setup() {
		super.setup();
	}

	@Test
	public void testGetAllPublishers ()	 {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/publisher/all"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":")));
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetPublisherByIdSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/publisher").queryParam("id", "1"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":1")));
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddNewPublisher () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			Publisher publisherTest = new Publisher("Prueba", "Prueba 1111", "Argentina");
			String jsonPublisher = getMapper().writeValueAsString(publisherTest);

			mvc.perform(post(getUrl()+"/publisher/add-publisher")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonPublisher))
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("Publisher created")));
			
			System.out.println(getSuccessTest());
			return;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * With ID 3
	 */
	@Test
	public void testUpdatePublisherSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			Publisher publisherTest = new Publisher("Update", "Update 1111", "Argentina");
			publisherTest.setId(3);
			String jsonPublisher = getMapper().writeValueAsString(publisherTest);

			mvc.perform(put(getUrl()+"/publisher/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonPublisher))
			.andExpect(status().isCreated());
			System.out.println(getSuccessTest());
			return;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Publisher ID: 6
	 */
	@Test
	public void testDeletePublisherById () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			
			mvc.perform(delete(getUrl()+"/publisher/delete").queryParam("id", "6"))
				.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
