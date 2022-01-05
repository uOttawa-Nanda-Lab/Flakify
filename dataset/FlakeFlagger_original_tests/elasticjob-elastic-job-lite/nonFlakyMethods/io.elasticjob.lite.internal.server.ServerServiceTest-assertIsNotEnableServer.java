@Test public void assertIsNotEnableServer(){
  when(jobNodeStorage.getJobNodeData("servers/127.0.0.1")).thenReturn(ServerStatus.DISABLED.name());
  assertFalse(serverService.isEnableServer("127.0.0.1"));
}
