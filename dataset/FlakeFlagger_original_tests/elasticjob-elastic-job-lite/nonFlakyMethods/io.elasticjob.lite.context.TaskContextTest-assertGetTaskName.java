@Test public void assertGetTaskName(){
  TaskContext actual=TaskContext.from(TaskNode.builder().build().getTaskNodeValue());
  assertThat(actual.getTaskName(),is("test_job@-@0@-@READY@-@slave-S0"));
}
