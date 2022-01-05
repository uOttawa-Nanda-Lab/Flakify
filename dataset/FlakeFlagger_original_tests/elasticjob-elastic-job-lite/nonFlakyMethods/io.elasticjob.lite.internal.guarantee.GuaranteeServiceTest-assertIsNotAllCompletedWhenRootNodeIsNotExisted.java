@Test public void assertIsNotAllCompletedWhenRootNodeIsNotExisted(){
  when(jobNodeStorage.isJobNodeExisted("guarantee/completed")).thenReturn(false);
  assertFalse(guaranteeService.isAllCompleted());
}
