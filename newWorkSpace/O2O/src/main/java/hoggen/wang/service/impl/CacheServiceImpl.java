package hoggen.wang.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import hoggen.wang.cache.JedisUtil;
import hoggen.wang.service.CacheService;

public class CacheServiceImpl implements CacheService {
	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Override
	public void removeFromCache(String keyPrefix) {
		// TODO Auto-generated method stub
		Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
		for (String key : keySet) {
			jedisKeys.del(key);
		}

	}

}
