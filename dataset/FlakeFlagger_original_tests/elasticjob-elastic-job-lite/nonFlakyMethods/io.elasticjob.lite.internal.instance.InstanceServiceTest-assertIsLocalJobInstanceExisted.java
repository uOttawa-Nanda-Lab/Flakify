@Test public void assertIsLocalJobInstanceExisted(){
  when(jobNodeStorage.isJobNodeExisted("instances/127.0.0.1@-@0")).thenReturn(true);
  assertTrue(instanceService.isLocalJobInstanceExisted());
}
