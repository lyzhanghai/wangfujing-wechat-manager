/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.utilRedisURIFactory.java
 * @Create By kong
 * @Create In 2016年3月3日 下午12:04:28
 *
 */
package wechat.util;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;

import com.lambdaworks.redis.RedisURI;

/**
 * @Comment
 * @Class Name RedisURIFactory
 * @Author kong
 * @Create In 2016年3月3日
 */
public class RedisURIFactory implements FactoryBean<RedisURI> {

	private String host;
	private int port;
	private String password;
	private long timeout;
	
	/**
	 * @Return the String host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @Param String host to set
	 */
	@Required
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @Return the int port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @Param int port to set
	 */
	@Required
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @Return the String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @Param String password to set
	 */
	@Required
	public void setPassword(String password) {
		this.password = password;
	}

	/**
   * @Return the long timeout
   */
  public long getTimeout() {
    return timeout;
  }

  /**
   * @Param long timeout to set
   */
  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  /* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public RedisURI getObject() throws Exception {
		RedisURI redisUri = new RedisURI();
		redisUri.setHost(host);
		redisUri.setPort(port);
		redisUri.setPassword(password);
		redisUri.setTimeout(timeout);
		return redisUri;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class<?> getObjectType() {
		return RedisURI.class;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return true;
	}

}
