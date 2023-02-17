package com.hexagonal.produtos.adapters.outbound.producer;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class GenericProducerSQS {

    private static final int MAX_BATCH_SEND_SQS = 10;

    @Autowired
    private AmazonSQS amazonSQS;


    public void sentToQueue(String message, String queue) {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queue)
                .withMessageBody(message);

        amazonSQS.sendMessage(sendMessageRequest);

    }

    public void sentToQueue(List<String> messages,String queue) {
        Lists.partition(messages, MAX_BATCH_SEND_SQS)
                .forEach(strings -> {
                    final AtomicInteger index = new AtomicInteger();
                    final List<SendMessageBatchRequestEntry> entries = strings.stream()
                            .parallel()
                            .map(message -> {
                                final String messageId = String.valueOf(index.getAndIncrement());
                                return new SendMessageBatchRequestEntry(messageId, message);
                            })
                            .collect(Collectors.toList());

                    final SendMessageBatchRequest sendMessageRequest = new SendMessageBatchRequest()
                            .withQueueUrl(queue)
                            .withEntries(entries);

                    amazonSQS.sendMessageBatch(sendMessageRequest);
                });
    }
}
