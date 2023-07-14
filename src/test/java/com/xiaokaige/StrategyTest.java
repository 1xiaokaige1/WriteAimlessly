package com.xiaokaige;

import com.alibaba.druid.support.json.JSONUtils;
import com.stl.im.core.model.param.UserInfo;
import com.stl.im.core.model.response.ResponseInfo;
import com.stl.im.core.service.interfaces.ImUserTokenService;
import com.stl.util.STLJsonUtils;
import com.xiaokaige.dto.SignalNoticeBO;
import com.xiaokaige.kafka.KfkService;
import com.xiaokaige.strategy.aware.DefineAware;
import com.xiaokaige.strategy.dao.TargetInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;

/**
 * @author zengkai
 * @date 2021/8/4 17:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StrategyTest {


    @Test
    public void test111(){
        BigDecimal originNumber = new BigDecimal(2.0003);
        BigDecimal resultNumber = originNumber.setScale(2, RoundingMode.CEILING);
        System.out.println("resultNumber = " + resultNumber);
    }

    /*@Autowired
    private DefineAware defineAware;

    @Test
    public void test() {
        TargetInterface targetStrategy = defineAware.getTargetStrategy(1);
        int countOne = targetStrategy.methodOne();
        int countTwo = targetStrategy.methodTwo();
        System.out.println("countOne: " + countOne);
        System.out.println("countTwo: " + countTwo);
    }*/

    @Autowired
    private KfkService kfkService;

    @Test
    public void test03(){
        SignalNoticeBO signalNoticeBO = new SignalNoticeBO();
        signalNoticeBO.setSignalAccountId(63455942775267329L);
        signalNoticeBO.setSignalTicket(1);
        signalNoticeBO.setFollowAccountId(63455942775267330L);
        signalNoticeBO.setFollowTicket(1);
        signalNoticeBO.setSignalAction(0);
        signalNoticeBO.setSignalType(0);
        signalNoticeBO.setSignalLots(10);
        signalNoticeBO.setSignalOpenPrice(BigDecimal.ONE);
        signalNoticeBO.setSignalOpenTime(LocalDateTime.now());
        signalNoticeBO.setSignalTakeProfit(BigDecimal.ONE);
        signalNoticeBO.setSignalStopLoss(BigDecimal.ONE);
        signalNoticeBO.setStatus(1);
        signalNoticeBO.setSignalSymbol("aa");
        signalNoticeBO.setFollowSymbol("bb");
        kfkService.sendMsg("trading.account.service.signal.test", STLJsonUtils.obj2json(signalNoticeBO));
    }

    @Test
    public void test000(){
        BigDecimal numberOne = new BigDecimal(10);
        numberOne.subtract(new BigDecimal(2));
        System.out.println("numberOne = " + numberOne);
    }

    @Test
    public void test007(){
        SymbolUpdateBO symbolUpdateBO = new SymbolUpdateBO();
        symbolUpdateBO.setNewCnt(1);
        symbolUpdateBO.setRemovedCnt(2);
        symbolUpdateBO.setSignalAccountId(76063664653737997L);
        SymbolUpdateBO.MismatchBO mismatchBO = new SymbolUpdateBO.MismatchBO();
        mismatchBO.setMismatchCnt(0);
        mismatchBO.setFollowAccountId(76063458495307786L);
        symbolUpdateBO.setMismatchList(Collections.singletonList(mismatchBO));
        kfkService.sendMsg("trading.account.service.symbol.update.test", STLJsonUtils.obj2json(symbolUpdateBO));
    }

    @Autowired
    private ImUserTokenService imUserTokenService;

    @Test
    public void test00(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(10168L);
        userInfo.setUserName("理周教育學苑");
        ResponseInfo responseInfo = imUserTokenService.registerUser(userInfo);
        System.out.println("responseInfo = " + responseInfo);
    }

    @Test
    public void test003(){
        /*Long id = 747841203445522802L;
        String binaryString = Long.toBinaryString(id);
        System.out.println("binaryString = " + binaryString);*/
        System.out.println("101001100000110111011001110010111111110001010110000101110010".length());
    }

    @Test
    public void test04(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = now.plusHours(-9);
        LocalDate localDate = date.toLocalDate();
        LocalDateTime needTime = LocalDateTime.of(localDate, LocalTime.MAX);
        System.out.println("needTime = " + needTime);
    }

}
