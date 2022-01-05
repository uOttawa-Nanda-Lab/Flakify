@Test public void assertRemoveJobSettings(){
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getScriptJobJson());
  jobSettingsAPI.removeJobSettings("test_job");
  verify(regCenter).remove("/test_job");
}
