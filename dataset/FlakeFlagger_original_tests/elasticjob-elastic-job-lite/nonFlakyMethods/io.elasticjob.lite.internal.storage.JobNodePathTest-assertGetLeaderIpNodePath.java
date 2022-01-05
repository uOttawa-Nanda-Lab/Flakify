@Test public void assertGetLeaderIpNodePath(){
  assertThat(jobNodePath.getLeaderHostNodePath(),is("/test_job/leader/election/instance"));
}
