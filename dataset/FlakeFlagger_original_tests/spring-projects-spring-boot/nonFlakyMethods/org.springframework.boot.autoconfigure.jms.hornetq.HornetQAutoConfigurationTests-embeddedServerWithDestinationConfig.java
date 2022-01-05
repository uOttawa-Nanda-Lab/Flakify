@Test public void embeddedServerWithDestinationConfig(){
  load(DestinationConfiguration.class);
  DestinationChecker checker=new DestinationChecker(this.context);
  checker.checkQueue("sampleQueue",true);
  checker.checkTopic("sampleTopic",true);
}
