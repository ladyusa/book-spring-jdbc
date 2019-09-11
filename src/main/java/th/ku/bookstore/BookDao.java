package th.ku.bookstore;

import java.util.List;

public interface BookDao {
    void insert(Book book);
    Book getOne(int id);
    List<Book> getAll();

    // also update and delete accounts
}
