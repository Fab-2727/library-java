package library;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import library.model.Author;

/**
 * This class test the controller methods that use the entity
 * {@linkplain Author}
 * 
 * @author fab
 *
 */
public class ControllerAuthorTest extends AbstractTest {

	@BeforeEach
	@Override
	protected void setup() {
		super.setup();
	}

	@Test
	public void testGetAllAuthorsSuccess() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			
			MvcResult rst = mvc.perform(get(getUrl()+"/author/all")).andReturn();
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ID: 1
	 */
	@Test
	public void testGetAuthorById () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/author").queryParam("id", "1"))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Name: Albert
	 */
	@Test
	public void testGetAuthorByName () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/author/by-name").queryParam("name", "Albert"))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddNewAuthor () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			Author authorTest = new Author("PRUEBA");
			String jsonAuthor = getMapper().writeValueAsString(authorTest);
			
			mvc.perform(post(getUrl()+"/author/add-author")
					.content(jsonAuthor)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
			
			System.out.println(getSuccessTest());
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
	
	/**
	 * Author's ID: 4
	 */
	@Test 
	public void testUpdateAuthor () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			Author authorTest = new Author("PRUEBA");
			authorTest.setId(4);
			String jsonAuthor = getMapper().writeValueAsString(authorTest);
			
			mvc.perform(put(getUrl()+"/author/update").content(jsonAuthor)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
			
			System.out.println(getSuccessTest());
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
