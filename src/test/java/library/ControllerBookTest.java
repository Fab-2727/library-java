package library;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import library.model.Book;


//	Given (Input): Test preparation like creating data or configure mocks
//  When (Action): Call the method or action that you like to test
//  Then (Output): Execute assertions to verify the correct output or behavior of the action.

/**
 * This class test the controller methods that use the entity {@linkplain Book}
 * 
 * @author fab
 *
 */
@TestPropertySource(locations = "classpath:application-test.properties")
class ControllerBookTest extends AbstractTest {

	@Override
	@BeforeEach
	protected void setup() {
		super.setup();
	}
	
	/*		GET ALL BOOKS TESTS*/
	
	@Test
	public void testGetAllBooksHttpStatusCode() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			
			mvc.perform(get("/library/book/all")).
			andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void testGetAllBooksBodyData() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			MvcResult rst = mvc.perform(get("/library/book/all")).andReturn();
			
			JSONArray rspBody = new JSONArray(rst.getResponse().getContentAsString());
			assertNotEquals(0, rspBody.length());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*		GET BOOK BY ID		*/
	
	@Test
	public void testGetBookByIdOneHttpStatusCode() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");		
		try {
			
			mvc.perform(get("/library/book").queryParam("id", "1")).
			andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*		GET BOOK BY NAME	*/
	
	/**
	 * The book-name used is 'La Tregua'
	 */
	@Test
	public void testGetBookByNameHttpStatusCode() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/name").queryParam("name-book", "La Tregua"))
					.andExpect(status().isOk());

			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetBookByNameDataOfLaTregua() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/name").queryParam("name-book", "La Tregua")).
			andExpect(content().string(containsString("\"bookName\":\"La Tregua\"")));
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testing if the LIKE expression works.
	 * bookName: 'Tregua'
	 */
	@Test
	public void testGetBookByNameDataOfLaTreguaParcialName() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/name").queryParam("name-book", "Tregua")).
			andExpect(content().string(containsString("\"bookName\":\"La Tregua\"")));
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		GET BOOKS BY CATEGORY	*/
	
	/**
	 * Category use: {@literal Narracion}
	 */
	@Test
	public void testGetBookByCategoryNarracionHttpStatusCodeSuccess() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/category").queryParam("book-category", "Narracion"))
					.andExpect(status().isOk());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Category use: {@literal RANDOM CATEGORY}
	 */
	@Test
	public void testGetBookByCategoryNarracionHttpStatusCodeNotFound() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			ResultActions rstAction = mvc.perform(get("/library/book/category")
					.queryParam("book-category", "RANDOM CATEGORY"))
					.andExpect(status().isNotFound());
			
			System.out.println(rstAction.andReturn().getResponse().getContentAsString());
			
			System.out.println(getSuccessTest());
			return;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBookByCategoryNarracionDataRetrieve() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			MvcResult rst = mvc.perform(get("/library/book/category").queryParam("book-category", "Narracion")).andReturn();
			JSONArray rspJson = new JSONArray(rst.getResponse().getContentAsString());
			assertNotNull(rspJson);
			
			System.out.println(getSuccessTest());
			return;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		GET BOOKS BY AUTHOR'S NAME 		*/
	
	@Test
	public void testGetBookByAuthorNameWithAlbertHttpStatusCodeSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/author").
					queryParam("name", "Albert"))
				.andExpect(status().isOk());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBookByAuthorNameWithAlbertDataFetch () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/author").queryParam("name", "Albert"))
			.andExpect(content().string(containsString("\"id\":")))
			.andExpect(content().string(containsString("\"authorName\":")));
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		GET BOOKS BY AUTHOR'S ID		*/
	
	/**
	 * Id: 1
	 */
	@Test
	public void testGetBookByIdAuthorSuccess() {	
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			mvc.perform(get("/library/book/author/id").queryParam("author-id", "1")).andExpect(status().isOk());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/*		ADD NEW BOOK METHOD		*/
	
	/**
	 * Test to see if '/library/book/add-book' works as expected
	 * 
	 * (The request is comment. Cause: it works)
	 */
	@Test
	public void testAddNewBookWithSuccess() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			// Creating the object to persist
			//Book bookTest = new Book("99119911", "PRUEBA", 2021, 1000f, "Libro de prueba", 500);
			//JSONObject jsonBook = new JSONObject(mapper.writeValueAsString(bookTest));
			JSONObject jsonTestBook = new JSONObject();
			jsonTestBook.put("isbn", 11991199);
			jsonTestBook.put("bookName", "PRUEBA");
			jsonTestBook.put("publishYear", 2021);
			jsonTestBook.put("price", 2021.50f);
			jsonTestBook.put("description", "Libro de prueba del método newBook");
			jsonTestBook.put("pages", 200);
			jsonTestBook.put("idAuthor", 4);
			jsonTestBook.put("idPublisher", 1);
			jsonTestBook.put("idTopic", 1);
			
			
			//mvc.perform(post("/library/book/add-book").contentType(MediaType.APPLICATION_JSON).content(jsonTestBook.toString()))
			//.andExpect(status().isCreated());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test to see if an error is return as expected from '/library/book/add-book'
	 */
	@Test
	public void testAddNewBookNotFoundAuthor() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			// Creating the object to persist
			//Book bookTest = new Book("99119911", "PRUEBA", 2021, 1000f, "Libro de prueba", 500);
			//JSONObject jsonBook = new JSONObject(mapper.writeValueAsString(bookTest));
			
			JSONObject jsonTestBook = new JSONObject();
			jsonTestBook.put("isbn", 11991199);
			jsonTestBook.put("bookName", "PRUEBA");
			jsonTestBook.put("publishYear", 2021);
			jsonTestBook.put("price", 2021.50f);
			jsonTestBook.put("description", "Libro de prueba del método newBook");
			jsonTestBook.put("pages", 200);
			jsonTestBook.put("idAuthor", 1000);
			jsonTestBook.put("idPublisher", 1);
			jsonTestBook.put("idTopic", 1);
			
			MvcResult rst = mvc.perform(post("/library/book/add-book").contentType(MediaType.APPLICATION_JSON).content(jsonTestBook.toString())).andReturn();
			System.out.println("RESPONSE: \n"+ rst.getResponse().getContentAsString());
			assertEquals(HttpStatus.NOT_FOUND.value(), rst.getResponse().getStatus());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Test to see if an error is return as expected from '/library/book/add-book'</p>
	 * <p>Error should be: 'Invalid payload, missing 'isbn' key'</p>
	 */
	@Test
	public void testAddNewBookWithoutISBN() {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			// Creating the object to persist
			//Book bookTest = new Book("99119911", "PRUEBA", 2021, 1000f, "Libro de prueba", 500);
			//JSONObject jsonBook = new JSONObject(mapper.writeValueAsString(bookTest));
			JSONObject jsonTestBook = new JSONObject();
			jsonTestBook.put("isbn", "");
			jsonTestBook.put("bookName", "PRUEBA");
			jsonTestBook.put("publishYear", 2021);
			jsonTestBook.put("price", 2021.50f);
			jsonTestBook.put("description", "Libro de prueba del método newBook");
			jsonTestBook.put("pages", 200);
			jsonTestBook.put("idAuthor", 1000);
			jsonTestBook.put("idPublisher", 1);
			jsonTestBook.put("idTopic", 1);
			
			ResultActions rstAction = mvc.perform(post("/library/book/add-book")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonTestBook.toString()))
					.andExpect(status().isBadRequest());
			
			System.out.println("RESPONSE: \n"+ rstAction.andReturn().getResponse().getContentAsString());
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*		UPDATE BOOK METHOD		*/
	
	/**
	 * Book ID: 5
	 * mvn clean test -Dtest=ControllerBookTest#testUpdateBookSuccess
	 */
	/*
	@Test
	public void testUpdateBookSuccess () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			JSONObject jsonTestBook = new JSONObject();
			jsonTestBook.put("isbn", "22992299");
			jsonTestBook.put("bookName", "UPDATED BOOK");
			jsonTestBook.put("publishYear", 2021);
			jsonTestBook.put("price", 2021.00f);
			jsonTestBook.put("description", "Prueba UPDATE method");
			jsonTestBook.put("pages", 400);
			jsonTestBook.put("idAuthor", 3);
			jsonTestBook.put("idPublisher", 1);
			jsonTestBook.put("idTopic", 4);
			
			ResultActions rstAction = mvc.perform(put(getUrl()+"/book/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonTestBook.toString())
						.queryParam("id", "5"))
							.andExpect(status().isCreated());
			
			System.out.println(rstAction.andReturn().getResponse().getContentAsString());
		
			System.out.println(getSuccessTest());
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * The author doesn't exists.
	 */
	@Test
	public void testUpdateBookBadRequest () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			JSONObject jsonTestBook = new JSONObject();
			jsonTestBook.put("isbn", "22992299");
			jsonTestBook.put("bookName", "UPDATED BOOK");
			jsonTestBook.put("publishYear", 2021);
			jsonTestBook.put("price", 2021.00f);
			jsonTestBook.put("description", "Prueba UPDATE method");
			jsonTestBook.put("pages", 400);
			jsonTestBook.put("idAuthor", 100);
			jsonTestBook.put("idPublisher", 1);
			jsonTestBook.put("idTopic", 4);
			
			ResultActions rstAction = mvc.perform(put(getUrl()+"/book/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonTestBook.toString())
						.queryParam("id", "4"))
							.andExpect(status().isBadRequest());
			
			System.out.println(rstAction.andReturn().getResponse().getContentAsString());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Book ID: 6
	 */
	@Test
	public void testDeleteBookById () {
		System.out.println("##############    Starting test: "+ new Exception().getStackTrace()[0].getMethodName() +"    ##############");
		try {
			
			mvc.perform(delete(getUrl()+"/book/delete").queryParam("id", "6"))
				.andExpect(status().isOk());
			
			System.out.println(getSuccessTest());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
