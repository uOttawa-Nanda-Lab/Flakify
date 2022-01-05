@Test public void assertIsNotAllStartedWhenRootNodeIsNotExisted(){
  when(jobNodeStorage.isJobNodeExisted("guarantee/started")).thenReturn(false);
  assertFalse(guaranteeService.isAllStarted());
}
