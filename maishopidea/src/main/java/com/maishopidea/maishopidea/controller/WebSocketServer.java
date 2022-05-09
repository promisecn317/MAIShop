package com.maishopidea.maishopidea.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author websocket  Service
 */
@ServerEndpoint(value = "/chatBox/{userId}")
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * Current Online Members
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();


    /**
     *   Methods called for successful connection Establishment
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String username) {
        sessionMap.put(username, session);
        log.info("User:username={}", username, sessionMap.size());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("userId", key);
            array.add(jsonObject);
        }
//        {"users": [{"username": "zhang"},{ "username": "admin"}]}
//        sendAllMessage(JSONUtil.toJsonStr(result));  // Backend sends messages to all clients
    }
    /**
     * Closure Methods for connection calls
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String username) {
        sessionMap.remove(username);
        log.info("A connection closed，clear username={}'s session", username, sessionMap.size());
    }
    /**
     * Methods called after receiving a client message
     * The backend receives a message from the client
     * onMessage It is a transit point for messages
     * Accept Browser side socket.send The json data sent over
     * JSON Format: {"from": "zhang", "text": "hello"}
     * @param message Messages sent from the client
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String username) {
        log.info("Server accepts username={}'s message :{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);
        String toUsername = obj.getStr("to"); // to表示发送给哪个用户
        String text = obj.getStr("text"); // 发送的消息  hello
        // {"to": "admin", "text": "text"}
        Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
        if (toSession != null) {
            // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
            // {"from": "zhang", "text": "hello"}
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("from", username);
            jsonObject.set("text", text);
            this.sendMessage(jsonObject.toString(), toSession);
            log.info("Send message{} to  client{}", jsonObject.toString(), toUsername);
        } else {
            log.info("Failed，NOT FOUND username={}的session", toUsername);
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Error");
        error.printStackTrace();
    }
    /**
     * Server send message to certain client
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("Server send message{} to certain client[{}]", message, toSession.getId());
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("Server send message Failed!", e);
        }
    }

//    /**
//     * Server send message to all client
//     */
//    private void sendAllMessage(String message) {
//        try {
//            for (Session session : sessionMap.values()) {
//                log.info("Server send message{} to certain client[{}]", message, Session.getId());
//                session.getBasicRemote().sendText(message);
//            }
//        } catch (Exception e) {
//            log.error("Server send message Failed!", e);
//        }
//    }
}
