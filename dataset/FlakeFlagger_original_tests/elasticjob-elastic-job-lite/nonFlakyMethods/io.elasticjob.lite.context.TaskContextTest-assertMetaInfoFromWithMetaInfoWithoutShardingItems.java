@Test public void assertMetaInfoFromWithMetaInfoWithoutShardingItems(){
  TaskContext.MetaInfo actual=TaskContext.MetaInfo.from("test_job@-@");
  assertThat(actual.getJobName(),is("test_job"));
  assertTrue(actual.getShardingItems().isEmpty());
}
