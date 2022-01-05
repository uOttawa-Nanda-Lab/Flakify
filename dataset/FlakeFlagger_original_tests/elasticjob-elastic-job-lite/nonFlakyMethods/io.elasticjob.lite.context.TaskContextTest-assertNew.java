@Test public void assertNew(){
  TaskContext actual=new TaskContext("test_job",Lists.newArrayList(0),ExecutionType.READY,"slave-S0");
  assertThat(actual.getMetaInfo().getJobName(),is("test_job"));
  assertThat(actual.getMetaInfo().getShardingItems().get(0),is(0));
  assertThat(actual.getType(),is(ExecutionType.READY));
  assertThat(actual.getSlaveId(),is("slave-S0"));
  assertThat(actual.getId(),startsWith(TaskNode.builder().build().getTaskNodeValue().substring(0,TaskNode.builder().build().getTaskNodeValue().length() - 1)));
}
