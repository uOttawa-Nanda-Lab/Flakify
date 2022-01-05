@Test public void assertRemoveJobNodeIfNeeded(){
  when(regCenter.isExisted("/test_job/config")).thenReturn(true);
  jobNodeStorage.removeJobNodeIfExisted("config");
  verify(regCenter).isExisted("/test_job/config");
  verify(regCenter).remove("/test_job/config");
}
