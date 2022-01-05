@Test public void testDumpDirectory() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setDumpDirectory("/dump");
  assertThat(application.getDumpDirectory(),equalTo("/dump"));
  Map<String,String> parameters=new HashMap<String,String>();
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry(Constants.DUMP_DIRECTORY,"/dump"));
}
