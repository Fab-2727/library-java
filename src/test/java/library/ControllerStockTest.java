package library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * This class test the controller methods that use the entity {@linkplain Stock}
 * 
 * @author fab
 *
 */
public class ControllerStockTest extends AbstractTest{
	
	@BeforeEach
	@Override
	protected void setup() {
		super.setup();
	}
	
	@Test
	public void testGetAllStock () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/stock/all"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":")));
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testGetStockByIdBookSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/book/stock/id-book").queryParam("id", "1"))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Using a wrong ID: 4543
	 */
	@Test
	public void testGetStockByIdBookBadRequest() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get(getUrl()+"/book/stock/id-book").queryParam("id", "4543"))
			.andExpect(status().isNotFound());
		
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test stock book
	 */
	 @Test
	 public void testAddNewStockOfBook () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");

		try {
			
			JSONObject jsonStockData = new JSONObject();
			jsonStockData.put("stockBook", 444);
			jsonStockData.put("idBook", 4);
			
			ResultActions rstAction = mvc.perform(post(getUrl()+"/stock")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonStockData.toString()));
			
			System.out.println(rstAction.andReturn().getResponse().getContentAsString());
			
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	
	/**
	 * Test update stock.
	 * 
	 * Book ID: 1
	 */
	@Test
	public void testUpdateStockByBookId () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("id-book", "1");
			params.add("stock", "1000");
			
			mvc.perform(put(getUrl()+"/stock/update").queryParams(params)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Book ID: 4
	 */
	@Test
	public void testDeleteStockByBookId () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			
			mvc.perform(delete(getUrl()+"/stock/delete").queryParam("id-book", "4"))
				.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
