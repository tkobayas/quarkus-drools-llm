package org.hybridai.refund;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.hybridai.refund.model.RefundAmount;
import org.hybridai.refund.model.SessionData;
import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;

@Singleton
public class DroolsRefundCalculator {

    @Inject
    KieRuntimeBuilder runtimeBuilder;

    public String checkRefund(SessionData sessionData) {
        KieSession kieSession = runtimeBuilder.newKieSession("refundCalc");
        kieSession.insert(sessionData.getCustomer());
        kieSession.insert(sessionData.getFlight());
        kieSession.fireAllRules();

        var refunds = kieSession.getInstancesOf(RefundAmount.class);

        if (refunds.isEmpty()) {
            return "申し訳ありません、 " + sessionData.getCustomer().getFullNameEasternStyle() + "様、 ご返金の対象となるものはありません。";
        }
        RefundAmount refund = refunds.iterator().next();
        return "ご迷惑をお掛けしました。" + sessionData.getCustomer().getFullNameEasternStyle() + "様、 ご返金の対象となる金額は " + (int) refund.getAmount() + "円 です。";
    }
}
