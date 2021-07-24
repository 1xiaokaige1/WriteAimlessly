package com.xiaokaige;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseTest {

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void test() throws SQLException {
        DruidPooledConnection conn = dataSource.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        InputStream in = DataBaseTest.class.getResourceAsStream("/test.sql");
        runner.runScript(new InputStreamReader(in));
    }
}