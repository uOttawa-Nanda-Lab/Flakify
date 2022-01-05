@Test public void assertIsAvailableServer(){
  when(jobNodeStorage.getJobNodeChildrenKeys("instances")).thenReturn(Collections.singletonList("127.0.0.1@-@0"));
  assertTrue(serverService.isAvailableServer("127.0.0.1"));
}
