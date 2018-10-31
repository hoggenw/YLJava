package hoggen.wang.service;

public interface CacheService {
	/**
	 * @注释 依据key前缀匹配该模式下的所有key-value
	 */

	void removeFromCache(String keyPrefix);
}
