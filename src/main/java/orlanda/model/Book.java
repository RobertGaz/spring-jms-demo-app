package orlanda.model;

public class Book {
    private String bookId;
    private String title;

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Book() {
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
}
