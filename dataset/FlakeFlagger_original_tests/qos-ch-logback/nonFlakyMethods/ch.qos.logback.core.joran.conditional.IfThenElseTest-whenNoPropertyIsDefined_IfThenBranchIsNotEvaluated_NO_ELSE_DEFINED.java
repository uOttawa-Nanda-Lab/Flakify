@Test public void whenNoPropertyIsDefined_IfThenBranchIsNotEvaluated_NO_ELSE_DEFINED() throws JoranException {
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "ifWithoutElse.xml");
  verifyConfig(new String[]{"BEGIN","END"});
  assertTrue(checker.isErrorFree(0));
}
