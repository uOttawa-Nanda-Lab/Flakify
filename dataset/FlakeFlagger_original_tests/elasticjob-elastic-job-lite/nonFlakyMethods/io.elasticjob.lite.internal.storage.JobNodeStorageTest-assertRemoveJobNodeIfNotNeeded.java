@Test public void assertRemoveJobNodeIfNotNeeded(){
  when(regCenter.isExisted("/test_job/config")).thenReturn(false);
  jobNodeStorage.removeJobNodeIfExisted("config");
  verify(regCenter).isExisted("/test_job/config");
  verify(regCenter,times(0)).remove("/test_job/config");
}
