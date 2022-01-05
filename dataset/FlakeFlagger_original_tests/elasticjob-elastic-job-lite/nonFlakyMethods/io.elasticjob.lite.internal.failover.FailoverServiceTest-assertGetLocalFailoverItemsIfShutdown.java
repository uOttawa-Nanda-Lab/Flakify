@Test public void assertGetLocalFailoverItemsIfShutdown(){
  assertThat(failoverService.getLocalFailoverItems(),is(Collections.<Integer>emptyList()));
  verify(jobNodeStorage,times(0)).getJobNodeChildrenKeys("sharding");
}
