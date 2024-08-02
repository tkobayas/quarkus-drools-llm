package org.hybridai.refund;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.inject.Singleton;
import org.hybridai.llmutil.StatelessChat;
import org.hybridai.refund.model.Flight;

@RegisterAiService(chatMemoryProviderSupplier = StatelessChat.MemorySupplier.class)
@Singleton
public interface FlightExtractor {

    @UserMessage("このテキストからフライトに関する情報を抽出してください '{text}'。レスポンスは JSON フォーマットのフライトのデータのみです。他の文は含めないでください。")
    Flight extractData(String text);
}

