@Test public void assertRegisterStart(){
  guaranteeService.registerStart(Arrays.asList(0,1));
  verify(jobNodeStorage).createJobNodeIfNeeded("guarantee/started/0");
  verify(jobNodeStorage).createJobNodeIfNeeded("guarantee/started/1");
}
