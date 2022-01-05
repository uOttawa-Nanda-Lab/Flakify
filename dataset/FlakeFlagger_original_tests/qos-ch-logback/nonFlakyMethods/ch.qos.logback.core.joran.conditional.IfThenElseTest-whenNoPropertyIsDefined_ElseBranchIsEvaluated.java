@Test public void whenNoPropertyIsDefined_ElseBranchIsEvaluated() throws JoranException {
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "if0.xml");
  verifyConfig(new String[]{"BEGIN","b","END"});
}
