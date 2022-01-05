@Test public void assertNewWithoutSlaveId(){
  TaskContext actual=new TaskContext("test_job",Lists.newArrayList(0),ExecutionType.READY);
  assertThat(actual.getSlaveId(),is("unassigned-slave"));
}
