@Test public void assertGetServerNodePathForServerIp(){
  assertThat(jobNodePath.getServerNodePath("ip0"),is("/test_job/servers/ip0"));
}
