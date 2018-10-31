package hoggen.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoggen.wang.dao.AreaDao;
import hoggen.wang.entity.Area;
import hoggen.wang.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao rAreaDao;

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return rAreaDao.queryArea();
	}

}
