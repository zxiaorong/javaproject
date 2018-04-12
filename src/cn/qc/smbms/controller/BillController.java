package cn.qc.smbms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.qc.smbms.pojo.Bill;
import cn.qc.smbms.service.bill.BillService;

@Controller
public class BillController {
	@Autowired
	private BillService bs;
	@RequestMapping(value ="/GetBillPageServlet",method={RequestMethod.GET}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getBillpage(String productName, String id) {
		int id1 = Integer.parseInt(id);
		int count = bs.count(productName, id1);
		return ""+count;
	}

	@RequestMapping(value="/GetBillAllOrLikeServlrt",method={RequestMethod.POST}, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getBillAllOrLike(String productName,String id,String pageNum,String pageSize) {
		int id1 = Integer.parseInt(id);
		int pageNum1 = Integer.parseInt(pageNum);
		int pageSize1= Integer.parseInt(pageSize);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("productName", productName);
		map.put("providerId", id1);
		map.put("pageNum", pageNum1);
		map.put("pageSize", pageSize1);
		List<Bill> bills = bs.getAllOrLikeBill(map);
		Gson g = new Gson();
		String jsonstr = g.toJson(bills);
		return jsonstr;
		
	}

	@RequestMapping(value="/GetBillByIdServlet",method={RequestMethod.GET},produces="application/json;charset=utf-8")
	@ResponseBody
	public ModelAndView GetBillByIdServlet(String id) {
		int id1=Integer.parseInt(id);
		Bill b = bs.getBillById(id1);
		ModelAndView mav = new ModelAndView("updatePage");
		mav.addObject("b", b);
		return mav;
	}

	@RequestMapping(value="/DeleteBillSeverlet",method={RequestMethod.GET},produces="application/json;charset=utf-8")
	@ResponseBody
	public String DeleteBillSeverlet(String id) {
		int id1 = Integer.parseInt(id);
		int ret = bs.deleteBill(id1);
		return ""+ret;
	}

	@RequestMapping(value="/AddBillServlet",method={RequestMethod.POST},produces="spring-applicationContext.xml")
	@ResponseBody
	public String AddBillServlet(HttpServletResponse response, HttpServletRequest request) {
		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productDesc = request.getParameter("productDesc");
		String productUnit = request.getParameter("productUnit");
		double productCount = Double.parseDouble(request.getParameter("productCount"));
		double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		int isPayment = Integer.parseInt(request.getParameter("isPayment"));
		int providerId = Integer.parseInt(request.getParameter("providerId"));
		Bill b = new Bill();
		b.setBillCode(billCode);
		b.setProductCount(productCount);
		b.setProductDesc(productDesc);
		b.setProductName(productName);
		b.setProductUnit(productUnit);
		b.setTotalPrice(totalPrice);
		b.setIsPayment(isPayment);
		b.setProviderId(providerId);
		int ret = bs.addBill(b);
		return ""+ret;

	}

	@RequestMapping(value="/UpdateBillSubmitServlet",method={RequestMethod.POST},produces="application/json;charset=utf-8")
	public void UpdateBillSubmitServlet(HttpServletResponse response, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productDesc = request.getParameter("productDesc");
		String productUnit = request.getParameter("productUnit");
		double productCount = Double.parseDouble(request.getParameter("productCount"));
		double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		int isPayment = Integer.parseInt(request.getParameter("isPayment"));
		int providerId = Integer.parseInt(request.getParameter("providerId"));
		Bill b = new Bill();
		b.setId(Integer.parseInt(id));
		b.setBillCode(billCode);
		b.setProductCount(productCount);
		b.setProductDesc(productDesc);
		b.setProductName(productName);
		b.setProductUnit(productUnit);
		b.setTotalPrice(totalPrice);
		b.setIsPayment(isPayment);
		b.setProviderId(providerId);
		int ret = bs.updateBill(b);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@RequestMapping("/addbill")
	public String addbill(){
		return "addbill";
	}
}
