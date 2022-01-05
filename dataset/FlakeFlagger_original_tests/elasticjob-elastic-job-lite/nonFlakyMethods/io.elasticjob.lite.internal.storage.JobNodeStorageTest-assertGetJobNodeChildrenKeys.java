@Test public void assertGetJobNodeChildrenKeys(){
  when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("host0","host1"));
  assertThat(jobNodeStorage.getJobNodeChildrenKeys("servers"),is(Arrays.asList("host0","host1")));
  verify(regCenter).getChildrenKeys("/test_job/servers");
}
