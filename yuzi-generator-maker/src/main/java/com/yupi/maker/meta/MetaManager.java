package com.yupi.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {

    private static volatile Meta meta;
//volatile关键字的作用当多个线程同时访问同一个 volatile
// 变量时，每个线程对这个变量的读取操作都会直接从主内存中读取最新的值，
// 而不是从线程本地缓存中读取。
    private MetaManager() {
        // 私有构造函数，防止外部实例化
    }
//单例双检索锁设计模式，这里是判断两次null
    public static Meta getMetaObject() {
//        这个null判断能够在初始化后的情况下，减少不必要的开销
        if (meta == null) {
//            加锁防止多个 thread 同时初始化 meta 对象
            synchronized (MetaManager.class) {
//                判断meta是否为空，为空才进行初始化，a抢到锁
//                初始化后，bc再次进行初始化防止多次初始化
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();
        // todo 校验和处理默认值
        return newMeta;
    }
}