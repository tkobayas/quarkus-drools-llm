package org.hybridai.refund;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.inject.Singleton;
import org.hybridai.llmutil.StatelessChat;
import org.hybridai.refund.model.Customer;

@RegisterAiService(chatMemoryProviderSupplier = StatelessChat.MemorySupplier.class)
@Singleton
public interface CustomerExtractor {

    @UserMessage("顧客の情報をこのテキストから抽出してください '{text}'。レスポンスは JSON フォーマットの顧客のデータのみです。他の文は含めないでください。" +
            "日本人の氏名は「姓」「名」の順に記載されていることが一般的です。")
    Customer extractData(String text);
}
