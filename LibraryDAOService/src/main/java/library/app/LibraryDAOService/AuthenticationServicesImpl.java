package library.app.LibraryDAOService;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.databind.JsonNode;
import library.app.dao.model.user_profile;
import library.app.dao.model.usercreds_info;
import library.app.services.UserServices;
import library.app.utilities.AppUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/library/validator")
public class AuthenticationServicesImpl {

	@Autowired
	UserServices userservices;
	
	

	// Validate login
	@RequestMapping(value="/login", method= RequestMethod.POST,produces= {"application/json"})
	public ResponseEntity<usercreds_info> userInfoAuthenticator(@RequestBody String info, HttpSession session) throws Exception {
		JsonNode node = AppUtils.parseJson(info);
		String uName = node.get("username").textValue();
		String uPassword = node.get("password").textValue();
		Optional<user_profile> data = userservices.findById(uName);
		usercreds_info userResponse = new usercreds_info();

		if (data.get() != null) {
			if (data.get().getIs_locked().equalsIgnoreCase("clean")) {
				if (data.get().getPassword().equals(uPassword)) {
					System.err.println(data.get().getEmail_id());
					session.setAttribute("sessionidinfo", data.get().getUser_id());
					session.setAttribute("PRINCIPAL_NAME", uName);
					userResponse.setUID(session.getId());
					userResponse.setMessage("Accepted");
					userResponse.setUser_profile((user_profile)data.get());
					
				} else {
					userResponse.setMessage("Wrong password");
				}
			} else {
				userResponse.setMessage("Account locked, reset password");
			}
		}
		
		return new ResponseEntity<usercreds_info>(userResponse,HttpStatus.ACCEPTED);

	}

	//remove sessionid
	@PostMapping("/invalidate")
	public Boolean closeSession(@RequestHeader(name="JSESSIONID") String id, HttpSession session) {
			
			session.invalidate();
			return true;
	}
}
