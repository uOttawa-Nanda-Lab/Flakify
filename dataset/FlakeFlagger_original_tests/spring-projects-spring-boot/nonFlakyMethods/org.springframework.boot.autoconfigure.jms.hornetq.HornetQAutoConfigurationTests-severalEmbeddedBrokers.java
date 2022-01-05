@Test public void severalEmbeddedBrokers(){
  load(EmptyConfiguration.class,"spring.hornetq.embedded.queues=Queue1");
  AnnotationConfigApplicationContext anotherContext=doLoad(EmptyConfiguration.class,"spring.hornetq.embedded.queues=Queue2");
  try {
    HornetQProperties properties=this.context.getBean(HornetQProperties.class);
    HornetQProperties anotherProperties=anotherContext.getBean(HornetQProperties.class);
    assertTrue("ServerId should not match",properties.getEmbedded().getServerId() < anotherProperties.getEmbedded().getServerId());
    DestinationChecker checker=new DestinationChecker(this.context);
    checker.checkQueue("Queue1",true);
    checker.checkQueue("Queue2",false);
    DestinationChecker anotherChecker=new DestinationChecker(anotherContext);
    anotherChecker.checkQueue("Queue2",true);
    anotherChecker.checkQueue("Queue1",false);
  }
  finally {
    anotherContext.close();
  }
}
