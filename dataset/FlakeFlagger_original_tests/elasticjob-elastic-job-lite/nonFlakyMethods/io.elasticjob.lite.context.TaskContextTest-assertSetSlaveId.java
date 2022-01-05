@Test public void assertSetSlaveId(){
  TaskContext actual=new TaskContext("test_job",Lists.newArrayList(0),ExecutionType.READY,"slave-S0");
  assertThat(actual.getSlaveId(),is("slave-S0"));
  actual.setSlaveId("slave-S1");
  assertThat(actual.getSlaveId(),is("slave-S1"));
}
