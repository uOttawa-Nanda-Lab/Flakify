@Test public void embeddedServiceWithCustomJmsConfiguration(){
  load(CustomJmsConfiguration.class,"spring.hornetq.embedded.queues=Queue1,Queue2");
  DestinationChecker checker=new DestinationChecker(this.context);
  checker.checkQueue("custom",true);
  checker.checkQueue("Queue1",false);
  checker.checkQueue("Queue2",false);
}
