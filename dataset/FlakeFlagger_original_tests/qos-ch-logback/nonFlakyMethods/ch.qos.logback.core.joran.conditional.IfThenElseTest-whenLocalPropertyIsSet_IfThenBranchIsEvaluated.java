@Test public void whenLocalPropertyIsSet_IfThenBranchIsEvaluated() throws JoranException {
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "if_localProperty.xml");
  verifyConfig(new String[]{"BEGIN","a","END"});
}
