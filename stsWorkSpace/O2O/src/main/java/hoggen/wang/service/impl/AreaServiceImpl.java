package hoggen.wang.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hoggen.wang.cache.JedisUtil;
import hoggen.wang.dao.AreaDao;
import hoggen.wang.entity.Area;
import hoggen.wang.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao rAreaDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;

	@Override
	public List<Area> getAreaList() {

		String key = AreaLIST;
		List<Area> areas = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			areas = rAreaDao.queryArea();
			String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(areas);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (jsonString != null) {
				jedisStrings.set(key, jsonString);
			}
		} else {
			String jsonString = jedisStrings.get(key);

			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);

			try {
				areas = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// TODO Auto-generated method stub
		return areas;
	}

}
