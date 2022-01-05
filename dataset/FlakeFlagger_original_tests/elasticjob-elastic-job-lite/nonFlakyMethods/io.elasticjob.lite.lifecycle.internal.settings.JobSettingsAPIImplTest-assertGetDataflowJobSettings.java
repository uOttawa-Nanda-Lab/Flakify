@Test public void assertGetDataflowJobSettings(){
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getDataflowJobJson());
  JobSettings actual=jobSettingsAPI.getJobSettings("test_job");
  assertJobSettings(actual,"DATAFLOW","io.elasticjob.lite.fixture.TestDataflowJob");
  verify(regCenter).get("/test_job/config");
}
