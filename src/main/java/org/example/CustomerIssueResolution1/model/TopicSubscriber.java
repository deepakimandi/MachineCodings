package org.example.CustomerIssueResolution1.model;

import org.example.subscriber.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    private final Topic topic;
    private final ISubscriber subscriber;
    private final AtomicInteger offset;

    public TopicSubscriber(Topic topic, ISubscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
        offset = new AtomicInteger(0);
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public Topic getTopic() {
        return topic;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
