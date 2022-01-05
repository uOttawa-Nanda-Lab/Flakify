@Test public void usePredefOptions() throws Exception {
  getMojo().setOptions("predef=['YUI','window','document','OnlineOpinion','xui']");
  getMojo().setTargetGroups("undef");
  getMojo().execute();
}
