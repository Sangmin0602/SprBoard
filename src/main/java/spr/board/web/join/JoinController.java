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
	
	@RequestMapping(value="/addr/umd", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String queryZipCode ( HttpServletRequest req) {
		String umd = req.getParameter("q");
		List<AddressVO> addrs = service.searchByUMD(umd);
		System.out.println(addrs);
		
		JSONObject root = new JSONObject();
		root.put("total", addrs.size());
		
		JSONArray arr = new JSONArray(); // for addresses 
		for ( int i = 0 ; i < addrs.size(); i++ ) {
			JSONObject addr = new JSONObject();
			AddressVO elem = addrs.get(i);
			addr.put("seq", elem.getSeq());
			addr.put("zipcode", elem.getZipCode());
			addr.put("mainaddr", elem.getMainAddress());
			addr.put("fulladdr", elem.getFullAddress());
			arr.add(addr);
		}
		root.put("data", arr);
		return root.toJSONString();
	}
}
