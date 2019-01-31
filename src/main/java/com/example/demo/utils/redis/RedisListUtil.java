package com.example.demo.utils.redis;

import com.example.demo.redisQuene.ObjectUtil;
import com.example.demo.redisSecKill.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.List;

/**
 * redis列表list操作
 */
@Slf4j
public class RedisListUtil {

    /**
     * 通过key向list头部添加字符串
     * @param key
     * @param strs 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public static Long lpush(String key, String... strs) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.lpush(key, strs);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }
    /**
     * 存储REDIS队列 顺序存储--存储二进制文件
     * @param  key reids键名
     * @param  value 键值
     */
    public static Long lpushByte(String key, Object value) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.lpush(key.getBytes(), ObjectUtil.object2Bytes(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }
    /**
     * 移除并返回列表 key 的尾元素。
     * @param key
     * @return
     */
    public static String rpop(String key){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.rpop(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }
    /**
     * 移除并返回列表 key 的尾元素。--对象要实现序列号接口
     * @param key
     * @return
     */
    public static Serializable rpopByte(String key){
        Jedis jedis = null;
        Serializable cls = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            byte[] bytes = jedis.rpop(key.getBytes());
            if(bytes!=null && bytes.length>0){
                cls = (Serializable)ObjectUtil.bytes2Object(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return cls;
    }
    /**
     * 移除并返回列表 key 的头元素。
     * @param key
     * @return
     */
    public static String lpop(String key){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.lpop(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }

    /**
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * BRPOP 除了弹出元素的位置和 BLPOP 不同之外，其他表现一致。
     * @param key
     * @throws InterruptedException
     */
    public static List<String> brpop(String key) throws InterruptedException {
        Jedis jedis = RedisUtil.getInstance().getJedis();
        //返回的list第一个元素为返回值列表的key值，第二个元素为返回的值
        List<String> brpop = jedis.brpop(5, key);
        System.out.println(brpop);
        jedis.brpop(5, key);
        return brpop;
    }
    /**
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
     * 它是 RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * BRPOP 除了弹出元素的位置和 BLPOP 不同之外，其他表现一致。
     * @param key
     * 返回的集合 list.get(0):key,list.get(1):value
     * @throws InterruptedException
     */
    public static List<byte[]>  brpopByte(String key) throws InterruptedException {
        Jedis jedis = RedisUtil.getInstance().getJedis();
        //返回的list第一个元素为返回值列表的key值，第二个元素为返回的值
        List<byte[]>  brpop = jedis.brpop(5, key.getBytes());
        System.out.println(brpop);
//        jedis.brpop(5, key);
        return brpop;
    }

    /**
     * 更新操作
     * 返回字符串：OK
     * @param key
     * @param index
     * @param value
     * @return
     */
    public static String lset(String key,int index,String value){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.lset(key,index,value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }

    /**
     * 查询list长度
     * @param key
     * @return
     */
    public static Long llen(String key){
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.llen(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;

    }

    /**
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param key
     * @param count
     * @param value
     * @return
     */
    public static Long lrem(String key,int count,String value){
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = RedisUtil.getInstance().getJedis();
            res = jedis.lrem(key,count,value);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            RedisUtil.getInstance().returnResource(RedisUtil.pool, jedis);
        }
        return res;
    }


}
