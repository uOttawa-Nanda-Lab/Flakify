@Test public void basicURL() throws JoranException {
  System.setProperty(INCLUDE_KEY,URL_TO_INCLUDE);
  tc.doConfigure(TOP_BY_URL);
  verifyConfig(new String[]{"IA","IB"});
}
