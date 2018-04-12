package cn.qc.smbms.dao.bill;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.qc.smbms.pojo.Bill;

public interface BillMapper {
	public List<Bill> getAllOrLikeBill(Map<Object, Object> billcon);
	public int count(@Param("productName")String productName,@Param("providerId")int providerId);
	public int addBill(Bill b);
	public int deleteBill(int id);
	public Bill getBillById(int id);
	public int updateBill(Bill b);
}
