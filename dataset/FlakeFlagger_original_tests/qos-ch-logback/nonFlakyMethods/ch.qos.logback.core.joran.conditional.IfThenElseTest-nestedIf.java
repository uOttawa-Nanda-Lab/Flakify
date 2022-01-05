@Test public void nestedIf() throws JoranException {
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "nestedIf.xml");
  verifyConfig(new String[]{"BEGIN","a","c","END"});
  assertTrue(checker.isErrorFree(0));
}
