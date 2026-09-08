/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.auth.websocket;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import top.continew.admin.auth.api.AuthSessionConstants;
import top.continew.admin.auth.api.AccessSessionValidator;
import top.continew.starter.messaging.websocket.dao.WebSocketSessionDao;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Refresh Session 撤销与 WebSocket 连接联动测试。 */
class AuthWebSocketSessionServiceTest {

    @Test
    @SuppressWarnings("unchecked")
    void shouldCloseLocalConnectionAndPublishClusterRevocation() throws Exception {
        RedissonClient redissonClient = mock(RedissonClient.class);
        RTopic topic = mock(RTopic.class);
        ObjectProvider<WebSocketSessionDao> sessionDaoProvider = mock(ObjectProvider.class);
        ObjectProvider<AccessSessionValidator> authSessionApiProvider = mock(ObjectProvider.class);
        WebSocketSessionDao sessionDao = mock(WebSocketSessionDao.class);
        WebSocketSession webSocketSession = mock(WebSocketSession.class);
        when(redissonClient.getTopic(anyString())).thenReturn(topic);
        when(topic.addListener(eq(String.class), any(MessageListener.class))).thenReturn(1);
        when(sessionDaoProvider.getIfAvailable()).thenReturn(sessionDao);
        when(sessionDao.listAllSessionIds()).thenReturn(Set.of("access-token"));
        when(sessionDao.get("access-token")).thenReturn(webSocketSession);
        when(webSocketSession.isOpen()).thenReturn(true);

        AuthWebSocketSessionService service = new AuthWebSocketSessionService(redissonClient,
            sessionDaoProvider, authSessionApiProvider);
        service.subscribe();
        try (MockedStatic<StpUtil> stpUtil = mockStatic(StpUtil.class)) {
            stpUtil.when(() -> StpUtil.getExtra("access-token",
                AuthSessionConstants.SESSION_ID_CLAIM)).thenReturn("session-1");

            service.notifyRevoked("session-1");
        } finally {
            service.unsubscribe();
        }

        verify(webSocketSession).close(CloseStatus.POLICY_VIOLATION);
        verify(sessionDao).delete("access-token");
        verify(topic).publish("session-1");
        verify(topic).removeListener(1);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldStillCloseLocalConnectionWhenClusterPublishFails() throws Exception {
        RedissonClient redissonClient = mock(RedissonClient.class);
        RTopic topic = mock(RTopic.class);
        ObjectProvider<WebSocketSessionDao> sessionDaoProvider = mock(ObjectProvider.class);
        ObjectProvider<AccessSessionValidator> authSessionApiProvider = mock(ObjectProvider.class);
        WebSocketSessionDao sessionDao = mock(WebSocketSessionDao.class);
        WebSocketSession webSocketSession = mock(WebSocketSession.class);
        when(redissonClient.getTopic(anyString())).thenReturn(topic);
        when(topic.addListener(eq(String.class), any(MessageListener.class))).thenReturn(1);
        when(topic.publish("session-1")).thenThrow(new IllegalStateException("Redis unavailable"));
        when(sessionDaoProvider.getIfAvailable()).thenReturn(sessionDao);
        when(sessionDao.listAllSessionIds()).thenReturn(Set.of("access-token"));
        when(sessionDao.get("access-token")).thenReturn(webSocketSession);
        when(webSocketSession.isOpen()).thenReturn(true);

        AuthWebSocketSessionService service = new AuthWebSocketSessionService(redissonClient,
            sessionDaoProvider, authSessionApiProvider);
        service.subscribe();
        try (MockedStatic<StpUtil> stpUtil = mockStatic(StpUtil.class)) {
            stpUtil.when(() -> StpUtil.getExtra("access-token",
                AuthSessionConstants.SESSION_ID_CLAIM)).thenReturn("session-1");

            service.notifyRevoked("session-1");
        } finally {
            service.unsubscribe();
        }

        verify(webSocketSession).close(CloseStatus.POLICY_VIOLATION);
        verify(sessionDao).delete("access-token");
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldCloseOnlyInvalidConnectionsDuringPeriodicValidation() throws Exception {
        RedissonClient redissonClient = mock(RedissonClient.class);
        ObjectProvider<WebSocketSessionDao> sessionDaoProvider = mock(ObjectProvider.class);
        ObjectProvider<AccessSessionValidator> authSessionApiProvider = mock(ObjectProvider.class);
        WebSocketSessionDao sessionDao = mock(WebSocketSessionDao.class);
        AccessSessionValidator authSessionApi = mock(AccessSessionValidator.class);
        WebSocketSession invalidSession = mock(WebSocketSession.class);
        when(sessionDaoProvider.getIfAvailable()).thenReturn(sessionDao);
        when(authSessionApiProvider.getIfAvailable()).thenReturn(authSessionApi);
        when(sessionDao.listAllSessionIds()).thenReturn(Set.of("invalid-token", "valid-token"));
        when(authSessionApi.isInvalid("invalid-token")).thenReturn(true);
        when(authSessionApi.isInvalid("valid-token")).thenReturn(false);
        when(sessionDao.get("invalid-token")).thenReturn(invalidSession);
        when(invalidSession.isOpen()).thenReturn(true);

        AuthWebSocketSessionService service = new AuthWebSocketSessionService(redissonClient,
            sessionDaoProvider, authSessionApiProvider);
        service.validateLocalSessions();

        verify(invalidSession).close(CloseStatus.POLICY_VIOLATION);
        verify(sessionDao).delete("invalid-token");
        verify(sessionDao, never()).delete("valid-token");
    }
}
