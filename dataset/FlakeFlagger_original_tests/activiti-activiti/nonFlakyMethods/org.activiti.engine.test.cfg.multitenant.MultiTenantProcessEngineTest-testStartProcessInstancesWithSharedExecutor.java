@Test public void testStartProcessInstancesWithSharedExecutor() throws Exception {
  setupProcessEngine(true);
  runProcessInstanceTest();
}
