@Test public void assertMetaInfoFromWithMetaInfo(){
  TaskContext.MetaInfo actual=TaskContext.MetaInfo.from("test_job@-@1");
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getShardingItems().get(0),is(1));
}
