package library.app.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import library.app.dao.model.file_logger;

@Repository
public interface FileLoggerService extends JpaRepository<file_logger,Integer>{

}
