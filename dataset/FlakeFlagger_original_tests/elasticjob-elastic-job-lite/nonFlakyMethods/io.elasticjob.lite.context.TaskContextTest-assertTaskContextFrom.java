@Test public void assertTaskContextFrom(){
  TaskContext actual=TaskContext.from(TaskNode.builder().build().getTaskNodeValue());
  assertThat(actual.getId(),Is.is(TaskNode.builder().build().getTaskNodeValue()));
  assertThat(actual.getMetaInfo().getJobName(),is("test_job"));
  assertThat(actual.getMetaInfo().getShardingItems().get(0),is(0));
  assertThat(actual.getType(),is(ExecutionType.READY));
  assertThat(actual.getSlaveId(),is("slave-S0"));
}
