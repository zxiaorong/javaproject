package cn.qc.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexContraller {
	@RequestMapping("gotoBillpage")
	public String gotoBillPage(){
		return "billpage";
	}
}
