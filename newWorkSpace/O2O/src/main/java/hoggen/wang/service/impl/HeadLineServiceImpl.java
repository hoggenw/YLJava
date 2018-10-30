package hoggen.wang.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import hoggen.wang.cache.JedisUtil;
import hoggen.wang.dao.HeadLineDao;
import hoggen.wang.dto.HeadLineExecution;
import hoggen.wang.entity.HeadLine;
import hoggen.wang.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;

	private static String HEADERLINELIST = "headerlineList";

	@Override
	@Transactional
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		// TODO Auto-generated method stub
		String key = HEADERLINELIST;
		List<HeadLine> headLines = null;
		ObjectMapper mapper = new ObjectMapper();
		if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
			key = key + "_" + headLineCondition.getEnableStatus();
		}
		if (!jedisKeys.exists(key)) {
			headLines = headLineDao.queryHeadLine(headLineCondition);
			String jsonString = mapper.writeValueAsString(headLines);
			if (jsonString != null) {
				jedisStrings.set(key, jsonString);
			}
		} else {
			String jsonString = jedisStrings.get(key);

			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);

			headLines = mapper.readValue(jsonString, javaType);

		}

		return headLines;
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
