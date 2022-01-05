@Test public void concurrentAccess() throws JoranException, InterruptedException {
  configure(FOLDER_PREFIX + "lbclassic203.xml");
  harness.execute(runnableArray);
  assertEquals(1,InstanceCountingAppender.INSTANCE_COUNT);
}
