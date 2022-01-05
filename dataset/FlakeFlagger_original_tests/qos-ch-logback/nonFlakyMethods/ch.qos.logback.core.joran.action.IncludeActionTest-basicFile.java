@Test public void basicFile() throws JoranException {
  System.setProperty(INCLUDE_KEY,INCLUDED_FILE);
  tc.doConfigure(TOP_BY_FILE);
  verifyConfig(new String[]{"IA","IB"});
}
