package library.app.LibraryDAOService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import library.app.dao.model.institution_info;
import library.app.exceptions.InstitutionExceptions;
import library.app.services.InstitutionServices;
import library.app.utilities.AppConstants;
import library.app.utilities.UniqueIdGenerator;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/library/school")
public class InstitutionServicesImpl {

	@Autowired
	InstitutionServices instiServices;

	@GetMapping("/getInfo")
	public institution_info getOneInstitutionInfo(@RequestParam(value = "institution_id") String ins_id)
			throws InstitutionExceptions, Exception {
		Optional<institution_info> info = instiServices.findById(ins_id);
		if (info.isPresent()) {
			return info.get();
		} else {
			throw new InstitutionExceptions("No Institution found for given ID");
		}
	}

	@GetMapping("/getAllInfo")
	public List<institution_info> getAllInstitutionInfo(@RequestParam(value = "status") String status)
			throws InstitutionExceptions, Exception {

		return instiServices.getInstitutionByStatus(status.toUpperCase());
	}

	@PostMapping("/saveInstitution")
	public @Valid institution_info saveInstitutionInfo(@Valid @RequestBody institution_info insData,
			@RequestParam(value = "adminId") String adminId) {
		insData.setInstitution_id(UniqueIdGenerator.getRandomUniversityID(insData.getInstitution_name()));
		System.err.println(insData.getInstitution_id());
		insData.setStatus(AppConstants.STATUS_ACTIVE);
		insData.setCreated_by(adminId);
		return instiServices.save(insData);
	}

	@PutMapping("/statusupdate")
	public boolean updateInstitutionStatus(@RequestParam(value = "status") String status,
			@RequestParam(value = "adminId") String adminId, @RequestParam(value = "institution_id") String inst_id) {
		int result = instiServices.updateInstitutionStatus(status, adminId, inst_id);
		return result > 0 ? true : false;
	}

	@PutMapping("/updateInstitution")
	public institution_info updateInstitutionProfileInfo(@Valid @RequestBody institution_info info) {
		institution_info data = instiServices.save(info);
		return data;
	}
}
