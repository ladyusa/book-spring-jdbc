package th.ku.bookstore;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImp implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Book book) {
        String query = "insert into book (id, name, price) values (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);
    }

    public Book getOne(int id) {
        String query = "select * from book where id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> getAll() {
        String query = "select * from book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }

    class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Book book = new Book(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"));
            return book;
        }
    }

}
