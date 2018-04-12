package cn.qc.smbms.service.bill;

import java.util.List;
import java.util.Map;

import cn.qc.smbms.pojo.Bill;

public interface BillService {
	public List<Bill> getAllOrLikeBill(Map<Object, Object> billcon);
	public int count(String productName,int providerId);
	public int addBill(Bill b);
	public int deleteBill(int id);
	public Bill getBillById(int id);
	public int updateBill(Bill b);
}
