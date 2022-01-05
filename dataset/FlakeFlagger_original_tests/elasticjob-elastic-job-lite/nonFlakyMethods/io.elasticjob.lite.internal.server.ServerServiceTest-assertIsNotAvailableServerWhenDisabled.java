@Test public void assertIsNotAvailableServerWhenDisabled(){
  when(jobNodeStorage.getJobNodeData("servers/127.0.0.1")).thenReturn(ServerStatus.DISABLED.name());
  assertFalse(serverService.isAvailableServer("127.0.0.1"));
}
