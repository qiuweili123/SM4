import com.sh.model.Users;
import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import java.io.IOException;
import java.util.Date;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-2
 * <p>Version: 1.0
 */

public class TestSpringCache {

    @Test
    public void testJcahe() throws IOException {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<Object, Object>();
        mutableConfiguration.setStoreByValue(false);  // otherwise value has to be Serializable
        cacheManager.createCache("user", mutableConfiguration);

        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(cacheManager);

        Long id = 1L;
        Users user = new Users(id, "zhang", "zhang@gmail.com", new Date());

        //根据缓存名字获取Cache
        Cache cache = jCacheCacheManager.getCache("user");

        //往缓存写数据(如果不存在 )
        cache.putIfAbsent(id, user);
        //从缓存读数据
        System.out.println("##userName :: " + cache.get(id, Users.class).getAddress());
        user = new Users(id, "zhang2", "zhang2@gmail.com", new Date());

        cache.put(id, user);

        //更新后从缓存读数据
        System.out.println("##userName :: " + cache.get(id, Users.class).getAddress());
    }

}
