@Test public void assertRegisterComplete(){
  guaranteeService.registerComplete(Arrays.asList(0,1));
  verify(jobNodeStorage).createJobNodeIfNeeded("guarantee/completed/0");
  verify(jobNodeStorage).createJobNodeIfNeeded("guarantee/completed/1");
}
