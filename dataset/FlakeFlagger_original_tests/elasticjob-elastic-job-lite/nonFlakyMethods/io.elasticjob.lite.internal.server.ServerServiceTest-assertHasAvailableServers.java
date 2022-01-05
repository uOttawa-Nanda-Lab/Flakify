@Test public void assertHasAvailableServers(){
  when(jobNodeStorage.getJobNodeChildrenKeys("servers")).thenReturn(Arrays.asList("127.0.0.1","127.0.0.2","127.0.0.3"));
  when(jobNodeStorage.getJobNodeData("servers/127.0.0.1")).thenReturn(ServerStatus.DISABLED.name());
  when(jobNodeStorage.getJobNodeChildrenKeys("instances")).thenReturn(Collections.singletonList("127.0.0.3@-@0"));
  assertTrue(serverService.hasAvailableServers());
}
