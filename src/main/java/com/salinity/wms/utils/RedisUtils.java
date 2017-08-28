package com.salinity.wms.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.salinity.wms.pojo.UserEntity;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
public class RedisUtils {

//    private static final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static JedisPool jedisPool = new JedisPool();

    private static RuntimeSchema<UserEntity> schema = RuntimeSchema.createFrom(UserEntity.class);

    public static UserEntity getItem(String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null) {
                    UserEntity user = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, user, schema);
                    return user;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String putItem(String key, UserEntity user) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //TimeOut
                int timeout = 60 * 60 * 24 * 7;//one week
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static List getItems (String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                List list = jedis.lrange(key, 0, -1);
                return list;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.toString());
            return null;
        }
    }

    public static String setItems (String key, List list) {

        for (Object obj : list) {

        }
        return null;
    }
}
