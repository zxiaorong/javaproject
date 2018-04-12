package cn.qc.smbms.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qc.smbms.dao.provider.ProviderMapper;
import cn.qc.smbms.pojo.Provider;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;
	public ProviderMapper getProviderMapper() {
		return providerMapper;
	}
	public void setProviderMapper(ProviderMapper providerMapper) {
		this.providerMapper = providerMapper;
	}
	@Override
	public List<Provider> getProvider() {
		List<Provider> pros=providerMapper.getProvider();
		
		return pros;
	}

}
