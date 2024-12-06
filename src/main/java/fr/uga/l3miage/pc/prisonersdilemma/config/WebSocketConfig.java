package fr.uga.l3miage.pc.prisonersdilemma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") .setAllowedOrigins("http://localhost:80").withSockJS() ;
    }



        @Override
        public void configureClientInboundChannel(ChannelRegistration registration) {
            registration.interceptors(new ChannelInterceptor() {
                @Override
                public Message<?> preSend(@NonNull Message<?>  message, @NonNull  MessageChannel channel) {
                    SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);

                    // Associer le sessionId dès que la connexion est établie
                    if (accessor.getMessageType() == SimpMessageType.CONNECT) {
                        String sessionId = accessor.getSessionId();
                        if (accessor.getSessionAttributes() == null) {
                            accessor.setSessionAttributes(new ConcurrentHashMap<>());
                        }
                        accessor.getSessionAttributes().put("sessionId", sessionId);
                    }
                    return message;
                }
            });
        }


}