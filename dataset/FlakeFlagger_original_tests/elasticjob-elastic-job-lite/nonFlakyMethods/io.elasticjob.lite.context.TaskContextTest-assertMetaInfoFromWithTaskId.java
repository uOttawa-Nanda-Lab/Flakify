@Test public void assertMetaInfoFromWithTaskId(){
  TaskContext.MetaInfo actual=TaskContext.MetaInfo.from("test_job@-@1@-@READY@-@unassigned-slave@-@0");
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getShardingItems().get(0),is(1));
}
