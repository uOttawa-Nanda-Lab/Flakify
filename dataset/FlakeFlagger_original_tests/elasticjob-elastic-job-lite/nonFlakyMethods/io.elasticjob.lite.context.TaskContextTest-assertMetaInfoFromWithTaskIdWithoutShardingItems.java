@Test public void assertMetaInfoFromWithTaskIdWithoutShardingItems(){
  TaskContext.MetaInfo actual=TaskContext.MetaInfo.from("test_job@-@@-@READY@-@unassigned-slave@-@0");
  assertThat(actual.getJobName(),is("test_job"));
  assertTrue(actual.getShardingItems().isEmpty());
}
