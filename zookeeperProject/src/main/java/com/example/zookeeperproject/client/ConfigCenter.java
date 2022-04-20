package com.example.zookeeperproject.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ConfigCenter {

    private final static String CONNECT_STR = "192.168.10.129:2181";

    private final static Integer SESSION_TIMEOUT = 30 * 1000;

    private ZooKeeper zooKeeper = null;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void setZooKeeper(){
        try {
            zooKeeper = new ZooKeeper(CONNECT_STR, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType() == Event.EventType.None
                            && watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        log.info("连接已建立");
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            MyConfig myConfig = new MyConfig();
            myConfig.setKey("anykey");
            myConfig.setName("anyname");
            ObjectMapper objectMapper = new ObjectMapper();
                byte[] bytes = objectMapper.writeValueAsBytes(myConfig);
                String s = zooKeeper.create("/myconfig", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                log.info("创建成功：" + s);

            byte[] data = zooKeeper.getData("/myconfig", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType() == Event.EventType.NodeDataChanged
                            && watchedEvent.getPath() != null && watchedEvent.getPath().equals("/myconfig")){
                        try {
                            log.info("PATH:{} 发生了数据的变化", watchedEvent.getPath());
                            byte[] data = zooKeeper.getData("/myconfig", this, null);
                            MyConfig newConfig = objectMapper.readValue(new String(data), MyConfig.class);
                            log.info("数据发生变化: {}",newConfig);
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException | JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, null);
            MyConfig value = objectMapper.readValue(new String(data), MyConfig.class);
            log.info("原始数据：" + value);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
