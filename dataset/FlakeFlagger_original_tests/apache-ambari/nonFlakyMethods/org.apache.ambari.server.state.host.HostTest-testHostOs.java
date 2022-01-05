@Test public void testHostOs() throws Exception {
  Clusters clusters=mock(Clusters.class);
  ActionQueue queue=mock(ActionQueue.class);
  ActionManager manager=mock(ActionManager.class);
  Injector injector=mock(Injector.class);
  doNothing().when(injector).injectMembers(any());
  HeartBeatHandler handler=new HeartBeatHandler(clusters,queue,manager,injector);
  String os=handler.getOsType("RedHat","6.1");
  Assert.assertEquals("redhat6",os);
  os=handler.getOsType("RedHat","6");
  Assert.assertEquals("redhat6",os);
  os=handler.getOsType("RedHat6","");
  Assert.assertEquals("redhat6",os);
}
