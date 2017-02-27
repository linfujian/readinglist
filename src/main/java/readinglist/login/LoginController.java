package readinglist.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readinglist.reader.Reader;
import readinglist.reader.ReaderRepository;

@Controller
@RequestMapping(value="/")
public class LoginController {

	@Autowired
	ReaderRepository readerRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String loginResult(Reader reader, Model model){
		String result;
		if(readerRepository.findOne(reader.getUsername()).getPassword().equals(reader.getPassword())) {
			//result = "redirect:/readingList/" + reader.getUsername();
			result = "redirect:/readingList/" + reader.getUsername();
		}
		else {
			model.addAttribute("loginName", reader.getUsername());
			result = "redirect:/";
		}
		
		return result;
			
	}
	
}
