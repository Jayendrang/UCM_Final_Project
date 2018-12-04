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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import library.app.dao.model.StubClass;
import library.app.dao.model.institution_info;
import library.app.exceptions.InstitutionExceptions;
import library.app.services.BookServices;
import library.app.services.InstitutionServices;
import library.app.utilities.AppConstants;
import library.app.utilities.AppUtils;
import library.app.utilities.UniqueIdGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/school")
public class InstitutionServicesImpl {

	@Autowired
	InstitutionServices instiServices;

	@Autowired
	BookServices bookservices;

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
	public List<institution_info> getAllInstitutionInfo() throws InstitutionExceptions, Exception {

		return instiServices.getInstitutionByStatus();
	}

	@PostMapping("/saveInstitution")
	public @Valid institution_info saveInstitutionInfo(@RequestBody institution_info insData,
			@RequestParam(value = "adminId") String adminId) {
		insData.setInstitution_id(UniqueIdGenerator.getRandomUniversityID(insData.getInstitution_name()));
		insData.setInstitution_email_domain(
				insData.getInstitution_email_id().substring(insData.getInstitution_email_id().lastIndexOf("@") + 1));
		insData.setInstitution_id(UniqueIdGenerator.getRandomUniversityID(insData.getInstitution_name()));
		insData.setServer_repo_path(insData.getInstitution_email_domain().replaceAll(".edu", ""));
		insData.setInstitution_invite_id(UniqueIdGenerator.getRandomID());
		System.err.println(insData.getInstitution_id());
		insData.setStatus(AppConstants.STATUS_ACTIVE);
		insData.setCreated_by(adminId);
		return instiServices.save(insData);
	}

	@PostMapping("/statusupdate")

	public boolean updateInstitutionStatus(@RequestBody String requestBody) throws Exception {
		JsonNode node = AppUtils.parseJson(requestBody);
		int result = 0;
		if (node.get("status").textValue().equalsIgnoreCase("DEACTIVATE")) {
			result = instiServices.updateInstitutionStatus("deactivated".toUpperCase(), node.get("userid").textValue(),
					node.get("institutionid").textValue());
			if (result > 0) {
				result = bookservices.removeBooksFromRepo(node.get("institutionid").textValue());
			}
		}
		System.err.println(node.get("userid").textValue());
		return result > 0 ? true : false;
	}

	@PostMapping("/updateInstitution")
	public institution_info updateInstitutionProfileInfo(@Valid @RequestBody institution_info info) {
		institution_info data = instiServices.save(info);
		return data;
	}

	@GetMapping("/count")
	public StubClass getInstitutionCount() {
		StubClass response = new StubClass();
		response.setKey("count");
		response.setValue(String.valueOf(instiServices.totalInstitutionCount()));
		return response;
	}
}
