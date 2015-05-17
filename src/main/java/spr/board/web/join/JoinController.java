package spr.board.web.join;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spr.board.model.AddressVO;

@Controller
public class JoinController {
	
	private IJoinService service ;
	
	@Autowired(required=true)
	public JoinController ( IJoinService js ) {
		service = js;
	}
	
	@RequestMapping(value="/join")
	public String showJoinPage ( ) {
		return "join";
	}
	
	@RequestMapping(value = "/addr/umd", 
			method = RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
	public String addrUmd(HttpServletRequest req) {
		String umd = req.getParameter("q");
		List<AddressVO> addr = service.searchByUmd(umd);
		
		System.out.println("addresses : " + addr);
		
		JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		
		JSONArray jsonArr = new JSONArray();
		for ( AddressVO avo : addr) {
			JSONObject jsonAvo = new JSONObject();
			jsonAvo.put("seq", avo.getSeq());
			jsonAvo.put("zipcode", avo.getZipCode());
			jsonAvo.put("mainAddr", avo.getMainAddr());
			jsonAvo.put("fullAddr", avo.getFullAddr());
			jsonArr.add(jsonAvo);
		}
		json.put("data", jsonArr);
		
		return json.toJSONString();
	}
}
