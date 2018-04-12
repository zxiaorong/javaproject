package cn.qc.smbms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.qc.smbms.pojo.Provider;
import cn.qc.smbms.service.provider.ProviderService;

@Controller
public class ProviderController {
	@Autowired
	private ProviderService ps;
	@RequestMapping(value="/GetProviderName",method={RequestMethod.GET},produces="application/json;charset=utf-8")
	@ResponseBody
	public String getProvider(){
		
		List<Provider> pros=ps.getProvider();
		Gson g = new Gson();
		String jsonstr=g.toJson(pros);
		return jsonstr;
	}
}
