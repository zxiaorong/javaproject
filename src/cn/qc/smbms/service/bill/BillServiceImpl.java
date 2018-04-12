package cn.qc.smbms.service.bill;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qc.smbms.dao.bill.BillMapper;
import cn.qc.smbms.pojo.Bill;

@Service("billService")
public class BillServiceImpl implements BillService{
	@Autowired
	private BillMapper billMapper;
	public BillMapper getBillMapper() {
		return billMapper;
	}

	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}

	@Override
	public List<Bill> getAllOrLikeBill(Map<Object, Object> billcon) {
		// TODO Auto-generated method stub
		List<Bill> bills=billMapper.getAllOrLikeBill(billcon);
		return bills;
	}

	@Override
	public int count(String productName, int providerId) {
		// TODO Auto-generated method stub
		int ret=billMapper.count(productName, providerId);
		return ret;
	}

	@Override
	public int addBill(Bill b) {
		// TODO Auto-generated method stub
		int ret=billMapper.addBill(b);
		return ret;
	}

	@Override
	public int deleteBill(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bill getBillById(int id) {
		// TODO Auto-generated method stub
		Bill ret=billMapper.getBillById(id);
		return ret;
	}

	@Override
	public int updateBill(Bill b) {
		int ret=billMapper.updateBill(b);
		return ret;
	}

	
}
