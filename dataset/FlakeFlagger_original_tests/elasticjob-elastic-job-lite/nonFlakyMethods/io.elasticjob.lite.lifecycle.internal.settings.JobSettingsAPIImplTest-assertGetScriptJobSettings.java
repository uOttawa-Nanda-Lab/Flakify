@Test public void assertGetScriptJobSettings(){
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getScriptJobJson());
  JobSettings actual=jobSettingsAPI.getJobSettings("test_job");
  assertJobSettings(actual,"SCRIPT","io.elasticjob.lite.api.script.ScriptJob");
  verify(regCenter).get("/test_job/config");
}
