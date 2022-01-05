@Test public void testOneShutdownHook(){
  DubboShutdownHook spyHook=Mockito.spy(DubboShutdownHook.getDubboShutdownHook());
  ClassPathXmlApplicationContext applicationContext=getApplicationContext(spyHook,false);
  applicationContext.refresh();
  applicationContext.close();
  Mockito.verify(spyHook,Mockito.times(1)).destroyAll();
}
