package com.xiaokaige.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class HandleCallBackMsg {

    /*
     * 应用 App Key
     */
	private String appKey;
	
	/*
	 * 发送用户 Id。
	 */
	private String fromUserId;
	
	/*
	 * 目标会话 Id，根据会话类型可能为单聊 Id、群聊 Id、聊天室 Id。
	 */
	private String targetId;
	
	/*
	 * 消息类型，文本消息 RC:TxtMsg 、 图片消息 RC:ImgMsg 其他消息类型请参见消息类型说明文档。
	 */
	private String msgType;
	
	/*
	 * 发送消息内容
	 */
	private String content;
	
	/*
	 * 会话类型，二人会话是 PERSON 、讨论组会话是 PERSONS 、群组会话是 GROUP 、聊天室会话是 TEMPGROUP 。
	 */
	private String channelType;
	
	/*
	 * 服务端收到客户端发送消息时的服务器时间（1970年到现在的毫秒数）。
	 */
	private String msgTimeStamp;
	
	/*
	 * 消息唯一标识。
	 */
	private String messageId;
	
	@Tolerate
    public HandleCallBackMsg() {
		
	}
}
