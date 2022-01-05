@Test public void embeddedServerWithDestinations(){
  load(EmptyConfiguration.class,"spring.hornetq.embedded.queues=Queue1,Queue2","spring.hornetq.embedded.topics=Topic1");
  DestinationChecker checker=new DestinationChecker(this.context);
  checker.checkQueue("Queue1",true);
  checker.checkQueue("Queue2",true);
  checker.checkQueue("QueueDoesNotExist",false);
  checker.checkTopic("Topic1",true);
  checker.checkTopic("TopicDoesNotExist",false);
}
