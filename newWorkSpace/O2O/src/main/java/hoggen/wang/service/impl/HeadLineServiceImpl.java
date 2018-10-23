package hoggen.wang.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import hoggen.wang.dao.HeadLineDao;
import hoggen.wang.dto.HeadLineExecution;
import hoggen.wang.entity.HeadLine;
import hoggen.wang.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		// TODO Auto-generated method stub
		return headLineDao.queryHeadLine(headLineCondition);
	}

	@Override
	public HeadLineExecution addHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution modifyHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLine(long headLineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}
