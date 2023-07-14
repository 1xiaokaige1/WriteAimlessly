package com.xiaokaige.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SignalNoticeBO {
    /**
     * 信号账户id
     */
    @ApiModelProperty(value = "信号账户id")
    private Long signalAccountId;
    /**
     * 信号订单id
     */
    @ApiModelProperty(value = "信号订单id")
    private Integer signalTicket;
    /**
     * 跟随账户id
     */
    @ApiModelProperty(value = "跟随账户id")
    private Long followAccountId;
    /**
     * 跟随订单id
     */
    @ApiModelProperty(value = "跟随订单id")
    private Integer followTicket;
    /**
     * 0 open 1 close
     */
    @ApiModelProperty(value = "信号动作，0 open 1 close")
    private Integer signalAction;
    /**
     * 0 buy 1 sell
     */
    @ApiModelProperty(value = "信号类型，0 buy 1 sell")
    private Integer signalType;
    /**
     * 订单量
     */
    @ApiModelProperty(value = "订单量")
    private Integer signalLots;
    /**
     * 开仓价格
     */
    @ApiModelProperty(value = "开仓价格")
    private BigDecimal signalOpenPrice;
    /**
     * 平仓价格
     */
    @ApiModelProperty(value = "平仓价格")
    private BigDecimal signalClosePrice;
    /**
     * 开仓时间
     */
    @ApiModelProperty(value = "开仓时间")
    private LocalDateTime signalOpenTime;
    /**
     * 平仓时间
     */
    @ApiModelProperty(value = "平仓时间")
    private LocalDateTime signalCloseTime;
    /**
     * 时区
     */
    @ApiModelProperty(value = "时区")
    private String signalTimeZone;
    /**
     * 止盈
     */
    @ApiModelProperty(value = "止盈")
    private BigDecimal signalTakeProfit;
    /**
     * 止损
     */
    @ApiModelProperty(value = "止损")
    private BigDecimal signalStopLoss;
    /**
     * 1 等待跟随 2 跟随成功 3 跟随失败 4 关闭跟随状态 5 禁止跟随 6 只跟多单 7 只跟空单 8 只跟随平仓
     */
    @ApiModelProperty(value = "1 等待跟随 2 跟随成功 3 跟随失败 4 关闭跟随状态 5 禁止跟随 6 只跟多单 7 只跟空单 8 只跟随平仓")
    private Integer status;
    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    private Integer errorCode;
    /**
     * 失败信息
     */
    @ApiModelProperty(value = "失败信息,失败时才有")
    private String msg;
    /**
     * 跟随品种
     */
    @ApiModelProperty(value = "跟随品种")
    private String followSymbol;
    /**
     * 信号品种
     */
    @ApiModelProperty(value = "信号品种")
    private String signalSymbol;
    /**
     * 盈亏(平仓后才有)
     */
    @ApiModelProperty(value = "盈亏(平仓后才有)")
    private BigDecimal signalProfit;
}