@Test public void assertGetFullPath(){
  assertThat(jobNodePath.getFullPath("node"),is("/test_job/node"));
}
