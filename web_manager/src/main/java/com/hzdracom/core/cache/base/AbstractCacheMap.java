package com.hzdracom.core.cache.base;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @Title: AbstractCacheMap.java
 * @Package com.hzdracom.cache
 * @Description:默认实现
 * @author 刘章
 * @date 2016年5月27日 下午9:35:02
 */
public abstract class AbstractCacheMap<K, V>
        implements
        Cache<K, V>
{
	public interface CacheListener<K, V>
	{
		public void remove(CacheObject<K, V> cacheObject);
		
		public void removeExpired(CacheObject<K, V> cacheObject);
	}
	
	public static class CacheObject<K2, V2>
	{
		public CacheObject(K2 key, V2 value, long ttl) {
			this.key = key;
			this.cachedObject = value;
			this.ttl = ttl;
			this.lastAccess = System.currentTimeMillis();
		}
		
		public final K2 key;
		public final V2 cachedObject;
		public long     lastAccess;  // 最后访问时间
		public long     accessCount; // 访问次数
		public long     ttl;         // 对象存活时间(time-to-live)
		                              
		public boolean isExpired() {
			if (ttl == 0) { return false; }
			return lastAccess + ttl < System.currentTimeMillis();
		}
		
		public K2 getKey() {
			return key;
		}
		
		public V2 getObject() {
			lastAccess = System.currentTimeMillis();
			accessCount++;
			return cachedObject;
		}
	}
	
	protected Map<K, CacheObject<K, V>>  cacheMap;
	
	private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
	private final Lock                   readLock  = cacheLock.readLock();
	private final Lock                   writeLock = cacheLock.writeLock();
	
	protected int                        cacheSize;                               // 缓存大小 , 0 -> 无限制
	                                                                               
	protected boolean                    existCustomExpire;                       //是否设置默认过期时间
	                                                                               
	public int getCacheSize() {
		return cacheSize;
	}
	
	protected long                defaultExpire;   // 默认过期时间, 0 -> 永不过期
	                                                
	protected CacheListener<K, V> abstractListener;
	
	public AbstractCacheMap(int cacheSize, long defaultExpire) {
		this.cacheSize = cacheSize;
		this.defaultExpire = defaultExpire;
	}
	
	public AbstractCacheMap(int cacheSize, long defaultExpire, CacheListener<K, V> listener) {
		this.cacheSize = cacheSize;
		this.defaultExpire = defaultExpire;
		this.abstractListener = listener;
	}
	
	public long getDefaultExpire() {
		return defaultExpire;
	}
	
	protected boolean isNeedClearExpiredObject() {
		return defaultExpire > 0 || existCustomExpire;
	}
	
	public void put(K key, V value) {
		put(key, value, defaultExpire);
	}
	
	public void put(K key, V value, long expire) {
		writeLock.lock();
		try
		{
			CacheObject<K, V> co = new CacheObject<K, V>(key, value, expire);
			if (expire != 0)
			{
				existCustomExpire = true;
			}
			if (isFull())
			{
				eliminate();
			}
			cacheMap.put(key, co);
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public V get(K key) {
		readLock.lock();
		try
		{
			CacheObject<K, V> co = cacheMap.get(key);
			if (co == null) { return null; }
			if (co.isExpired() == true)
			{
				cacheMap.remove(key);
				return null;
			}
			return co.getObject();
		}
		finally
		{
			readLock.unlock();
		}
	}
	
	public int clearExpired() {
		writeLock.lock();
		try
		{
			return eliminateCache();
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public final int eliminate() {
		writeLock.lock();
		try
		{
			return eliminateCache();
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	/**
	 * 淘汰对象具体实现
	 * 
	 * @return
	 */
	protected abstract int eliminateCache();
	
	public boolean isFull() {
		if (cacheSize == 0)
		{//o -> 无限制
			return false;
		}
		return cacheMap.size() >= cacheSize;
	}
	
	public void remove(K key) {
		writeLock.lock();
		try
		{
			cacheMap.remove(key);
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public V removeReturn(K key) {
		writeLock.lock();
		try
		{
			CacheObject<K, V> cacheObject = cacheMap.remove(key);
			return cacheObject == null ? null : cacheObject.getObject();
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public void clear() {
		writeLock.lock();
		try
		{
			cacheMap.clear();
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	public int size() {
		return cacheMap.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
}
