@Test public void trackerShouldHonorMaxComponentsParameter(){
  List<Appender<Object>> appenderList=new ArrayList<Appender<Object>>();
  int max=10;
  appenderTracker.setMaxComponents(max);
  for (int i=0; i < (max + 1); i++) {
    Appender a=appenderTracker.getOrCreate(key + "-" + i,now++);
    appenderList.add(a);
  }
  appenderTracker.removeStaleComponents(now++);
  assertEquals(max,appenderTracker.allKeys().size());
  assertNull(appenderTracker.find(key + "-" + 0));
  assertFalse(appenderList.get(0).isStarted());
}
