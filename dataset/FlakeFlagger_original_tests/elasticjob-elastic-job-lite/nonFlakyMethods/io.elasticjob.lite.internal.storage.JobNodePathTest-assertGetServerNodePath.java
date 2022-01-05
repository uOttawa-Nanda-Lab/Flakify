@Test public void assertGetServerNodePath(){
  assertThat(jobNodePath.getServerNodePath(),is("/test_job/servers"));
}
