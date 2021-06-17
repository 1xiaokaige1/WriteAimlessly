package com.xiaokaige.predicate;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zengkai
 * @date 2021/6/17 8:58
 */
@FunctionalInterface
public interface TestPredicate {

    /**
     * 处理文件流
     * @param br
     * @return
     */
    String processFile(BufferedReader br) throws IOException;
}
