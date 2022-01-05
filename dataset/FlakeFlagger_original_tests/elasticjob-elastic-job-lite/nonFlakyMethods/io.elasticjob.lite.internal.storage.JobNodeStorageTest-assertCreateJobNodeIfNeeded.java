@Test public void assertCreateJobNodeIfNeeded(){
  when(regCenter.isExisted("/test_job")).thenReturn(true);
  when(regCenter.isExisted("/test_job/config")).thenReturn(false);
  jobNodeStorage.createJobNodeIfNeeded("config");
  verify(regCenter).isExisted("/test_job");
  verify(regCenter).isExisted("/test_job/config");
  verify(regCenter).persist("/test_job/config","");
}
