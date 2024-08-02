package org.hybridai.refund;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.inject.Singleton;
import org.hybridai.llmutil.StatefulChat;

@RegisterAiService(chatMemoryProviderSupplier = StatefulChat.MemorySupplier.class)
@Singleton
public interface FlightChatService {

    @SystemMessage("<<SYS>>あなたは航空会社のチャットボットです。" +
            "あなたの目的は、質問をして顧客のフライトについての情報を収集し、どのような問題があったかを把握することです<</SYS>>")
    @UserMessage("""
        顧客にフライトの便名と遅延時間について質問してください。

        +++
        {message}
        +++
        """)
    String chat(@MemoryId String sessionId, String message);

}
