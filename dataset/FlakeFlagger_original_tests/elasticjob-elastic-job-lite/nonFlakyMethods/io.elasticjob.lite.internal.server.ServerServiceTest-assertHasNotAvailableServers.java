@Test public void assertHasNotAvailableServers(){
  when(jobNodeStorage.getJobNodeChildrenKeys("servers")).thenReturn(Arrays.asList("127.0.0.1","127.0.0.2"));
  when(jobNodeStorage.getJobNodeData("servers/127.0.0.2")).thenReturn(ServerStatus.DISABLED.name());
  when(jobNodeStorage.getJobNodeChildrenKeys("instances")).thenReturn(Arrays.asList("127.0.0.2@-@0","127.0.0.2@-@1"));
  assertFalse(serverService.hasAvailableServers());
}
