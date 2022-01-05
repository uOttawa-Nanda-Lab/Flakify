@Test public void assertClearAllCompletedInfo(){
  guaranteeService.clearAllCompletedInfo();
  verify(jobNodeStorage).removeJobNodeIfExisted("guarantee/completed");
}
