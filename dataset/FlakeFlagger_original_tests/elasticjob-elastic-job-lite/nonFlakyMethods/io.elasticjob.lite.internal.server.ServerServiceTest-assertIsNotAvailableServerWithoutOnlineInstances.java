@Test public void assertIsNotAvailableServerWithoutOnlineInstances(){
  when(jobNodeStorage.getJobNodeChildrenKeys("instances")).thenReturn(Collections.singletonList("127.0.0.2@-@0"));
  assertFalse(serverService.isAvailableServer("127.0.0.1"));
}
