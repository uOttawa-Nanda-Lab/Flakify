@Test public void testTwoShutdownHook(){
  DubboShutdownHook spyHook=Mockito.spy(DubboShutdownHook.getDubboShutdownHook());
  ClassPathXmlApplicationContext applicationContext=getApplicationContext(spyHook,true);
  applicationContext.refresh();
  applicationContext.close();
  try {
    spyHook.start();
    spyHook.join();
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  Mockito.verify(spyHook,Mockito.times(2)).destroyAll();
}
