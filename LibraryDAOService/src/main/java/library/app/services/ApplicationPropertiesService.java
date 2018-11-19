package library.app.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import library.app.dao.model.StubClass;
import library.app.dao.model.app_properties;

@Repository
public interface ApplicationPropertiesService extends JpaRepository<app_properties, String>{
	
	@Query("SELECT property_id,app_property FROM app_properties app WHERE property_id LIKE 'RQ%'")
	public String[] getProperties();
}
