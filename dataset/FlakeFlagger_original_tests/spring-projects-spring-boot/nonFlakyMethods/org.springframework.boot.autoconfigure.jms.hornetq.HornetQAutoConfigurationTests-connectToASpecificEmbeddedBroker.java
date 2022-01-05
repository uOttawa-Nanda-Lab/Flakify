@Test public void connectToASpecificEmbeddedBroker(){
  load(EmptyConfiguration.class,"spring.hornetq.embedded.serverId=93","spring.hornetq.embedded.queues=Queue1");
  AnnotationConfigApplicationContext anotherContext=doLoad(EmptyConfiguration.class,"spring.hornetq.mode=embedded","spring.hornetq.embedded.serverId=93","spring.hornetq.embedded.enabled=false");
  try {
    DestinationChecker checker=new DestinationChecker(this.context);
    checker.checkQueue("Queue1",true);
    DestinationChecker anotherChecker=new DestinationChecker(anotherContext);
    anotherChecker.checkQueue("Queue1",true);
  }
  finally {
    anotherContext.close();
  }
}
