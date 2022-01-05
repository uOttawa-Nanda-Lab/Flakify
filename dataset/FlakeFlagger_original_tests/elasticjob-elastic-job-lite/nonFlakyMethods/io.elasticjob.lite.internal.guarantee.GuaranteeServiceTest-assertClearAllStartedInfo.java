@Test public void assertClearAllStartedInfo(){
  guaranteeService.clearAllStartedInfo();
  verify(jobNodeStorage).removeJobNodeIfExisted("guarantee/started");
}
