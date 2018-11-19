package library.liberaryservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.app.dao.model.books_info;

@Repository
public interface  BookServices	extends JpaRepository<books_info, String> {

}
