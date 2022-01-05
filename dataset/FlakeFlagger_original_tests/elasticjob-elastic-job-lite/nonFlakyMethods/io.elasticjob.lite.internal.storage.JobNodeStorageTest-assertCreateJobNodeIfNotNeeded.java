@Test public void assertCreateJobNodeIfNotNeeded(){
  when(regCenter.isExisted("/test_job")).thenReturn(true);
  when(regCenter.isExisted("/test_job/config")).thenReturn(true);
  jobNodeStorage.createJobNodeIfNeeded("config");
  verify(regCenter).isExisted("/test_job");
  verify(regCenter).isExisted("/test_job/config");
  verify(regCenter,times(0)).persist("/test_job/config","");
}
