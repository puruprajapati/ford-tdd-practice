package com.example.fordtdd2.ref;

public class RestAssured {

  @BeforeClass
  public static void setup() {
    RestAssured.port = Integer.valueOf(8080);
    RestAssured.baseURI = "http://localhost";
    RestAssured.basePath = "";
  }

  @Test
  public void testGetOneBook() {
    // add the book to be fetched
    Book book = new Book("878", "Book 123", 18.95, "Joe Smith");
    given()
      .contentType("application/json")
      .body(book)
      .when().post("/books").then()
      .statusCode(200);
    // test getting the book
    given()
      .when()
      .get("books/878")
      .then()
      .contentType(ContentType.JSON)
      .and()
      .body("isbn", equalTo("878"))
      .body("title", equalTo("Book 123"))
      .body("price", equalTo(18.95f))
      .body("author", equalTo("Joe Smith"));

    //cleanup
    given()
      .when()
      .delete("books/878");
  }

  @Test
  public void testGetAllBooks() {
    // add the book to be fetched
    Book book = new Book("878", "Book 123", 18.95, "Joe Smith");
    given()
      .contentType("application/json")
      .body(book)
      .when().post("/books").then()
      .statusCode(200)
      .contentType(ContentType.JSON)
      .body("isbn", hasSize(1));
    // test getting the book
//    given()
//      .when()
//      .get("books/878")
//      .then()
//      .contentType(ContentType.JSON)
//      .and()
//      .body("isbn", equalTo("878"))
//      .body("title", equalTo("Book 123"))
//      .body("price", equalTo(18.95f))
//      .body("author", equalTo("Joe Smith"));

    //cleanup
    given()
      .when()
      .delete("books/878");
  }


}


public class Books {
  private Collection<Book> books = new ArrayList<Book>();

  public Collection<Book> getBooks() {
    return books;
  }

  public void setBooks(Collection<Book> books) {
    this.books = books;
  }
}