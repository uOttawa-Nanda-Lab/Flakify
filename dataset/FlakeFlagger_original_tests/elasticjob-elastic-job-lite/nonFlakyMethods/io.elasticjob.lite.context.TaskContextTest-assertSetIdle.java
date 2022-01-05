@Test public void assertSetIdle(){
  TaskContext actual=new TaskContext("test_job",Lists.newArrayList(0),ExecutionType.READY,"slave-S0");
  assertFalse(actual.isIdle());
  actual.setIdle(true);
  assertTrue(actual.isIdle());
}
