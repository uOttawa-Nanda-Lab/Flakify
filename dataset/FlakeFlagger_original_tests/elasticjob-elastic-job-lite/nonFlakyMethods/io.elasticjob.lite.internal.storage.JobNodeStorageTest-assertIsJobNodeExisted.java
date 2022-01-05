@Test public void assertIsJobNodeExisted(){
  when(regCenter.isExisted("/test_job/config")).thenReturn(true);
  assertTrue(jobNodeStorage.isJobNodeExisted("config"));
  verify(regCenter).isExisted("/test_job/config");
}
