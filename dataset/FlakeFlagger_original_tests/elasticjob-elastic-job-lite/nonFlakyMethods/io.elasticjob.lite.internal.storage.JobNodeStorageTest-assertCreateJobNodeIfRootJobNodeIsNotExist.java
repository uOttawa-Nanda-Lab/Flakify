@Test public void assertCreateJobNodeIfRootJobNodeIsNotExist(){
  when(regCenter.isExisted("/test_job")).thenReturn(false);
  when(regCenter.isExisted("/test_job/config")).thenReturn(true);
  jobNodeStorage.createJobNodeIfNeeded("config");
  verify(regCenter).isExisted("/test_job");
  verify(regCenter,times(0)).isExisted("/test_job/config");
  verify(regCenter,times(0)).persist("/test_job/config","");
}
