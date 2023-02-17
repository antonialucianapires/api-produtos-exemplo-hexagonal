package com.hexagonal.produtos.adapters.outbound.jobs;

import com.hexagonal.produtos.adapters.outbound.producer.GenericProducerSQS;
import com.hexagonal.produtos.application.ports.inbound.CreateProductUseCasePort;
import com.hexagonal.produtos.application.ports.inbound.GetProductsUseCasePort;
import com.hexagonal.produtos.application.ports.inbound.SendProductsForDistributionUseCasePort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DistributeProductsSchedulingJob {

    private final GenericProducerSQS producerSQS;
    private final GetProductsUseCasePort getProductsUseCasePort;

    private final CreateProductUseCasePort createProductUseCasePort;
    private final SendProductsForDistributionUseCasePort sendProductsForDistributionUseCasePort;

    public DistributeProductsSchedulingJob(GenericProducerSQS producerSQS, GetProductsUseCasePort getProductsUseCasePort, CreateProductUseCasePort createProductUseCasePort, SendProductsForDistributionUseCasePort sendProductsForDistributionUseCasePort) {
        this.producerSQS = producerSQS;
        this.getProductsUseCasePort = getProductsUseCasePort;
        this.createProductUseCasePort = createProductUseCasePort;
        this.sendProductsForDistributionUseCasePort = sendProductsForDistributionUseCasePort;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void toDistribute() {

        var start = System.currentTimeMillis();

        var products = getProductsUseCasePort.get();

        sendProductsForDistributionUseCasePort.send(products);

        System.out.println("TEMPO TOTAL: " + (System. currentTimeMillis() - start) + "ms");

    }
}
