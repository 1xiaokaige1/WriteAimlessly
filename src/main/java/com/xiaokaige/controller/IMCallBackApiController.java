package com.xiaokaige.controller;

import javax.servlet.http.HttpServletRequest;

import com.xiaokaige.entity.HandleCallBackMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.hutool.json.JSONObject;

@Controller
@RequestMapping("/v1/api/im")
public class IMCallBackApiController {
    private static final Logger logger = LoggerFactory.getLogger(IMCallBackApiController.class);
       
   /**
     * 接收即时通讯IM消息回调
     * @param request
     * @param appKey		应用 App Key
     * @param fromUserId	发送用户 Id。
     * @param targetId		目标会话 Id，根据会话类型可能为单聊 Id、群聊 Id、聊天室 Id。
     * @param msgType		消息类型，文本消息 RC:TxtMsg 、 图片消息 RC:ImgMsg 其他消息类型请参见消息类型说明文档。
     * @param content		发送消息内容
     * @param channelType	会话类型，二人会话是 PERSON 、讨论组会话是 PERSONS 、群组会话是 GROUP 、聊天室会话是 TEMPGROUP 。
     * @param msgTimeStamp	服务端收到客户端发送消息时的服务器时间（1970年到现在的毫秒数）。
     * @param messageId		消息唯一标识。
     * @return JSONObject	{ "pass": 1 }   1 表示正常下发此条消息，0 表示拒绝不下发此条消息。
     */
    @PostMapping("/callBackEvent")
    @ResponseBody
    public JSONObject callBackEvent(HttpServletRequest request,String appKey,String fromUserId,String targetId,
    		String msgType,String content,String channelType,String msgTimeStamp,String messageId) {
    	logger.info("callBackEvent function startTime:{}", System.currentTimeMillis());
    	logger.info("----------收到IM 给到的回调消息appKey={},  fromUserId={},targetId={},msgType={},content={},channelType={},msgTimeStamp={},messageId={}-----------", 
    			appKey, fromUserId, targetId, msgType, content, channelType, msgTimeStamp, messageId);
    	JSONObject ret = new JSONObject() {
    		{put("pass", 1);}
    	};
        try {
        	HandleCallBackMsg handleCallBackMsg = HandleCallBackMsg
        			.builder()
        			.appKey(appKey)
        			.fromUserId(fromUserId)
        			.targetId(targetId)
        			.msgType(msgType)
        			.content(content)
        			.channelType(channelType)
        			.msgTimeStamp(msgTimeStamp)
        			.messageId(messageId)
        			.build();
            //TO-DO handleCallBackMsg 实体类里就是回调的所有数据，此时就是根据自己的项目 串行或者异步把消息入库或者别的业务逻辑
          
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ret.put("pass", 0);
        }
        logger.info("callBackEvent function endTime:{}", System.currentTimeMillis());
        return ret;
    }
 
}
