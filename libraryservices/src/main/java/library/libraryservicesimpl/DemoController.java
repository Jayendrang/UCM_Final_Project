package library.libraryservicesimpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

	@GetMapping("/get/{univId}")
	public String getData(@PathVariable(value = "univId") String instituionId) {
		return "UniversityName :"+instituionId;
	}
}
