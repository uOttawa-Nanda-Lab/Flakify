@Test public void assertGetMetaInfo(){
  TaskContext actual=new TaskContext("test_job",Lists.newArrayList(0),ExecutionType.READY,"slave-S0");
  assertThat(actual.getMetaInfo().toString(),is("test_job@-@0"));
}
