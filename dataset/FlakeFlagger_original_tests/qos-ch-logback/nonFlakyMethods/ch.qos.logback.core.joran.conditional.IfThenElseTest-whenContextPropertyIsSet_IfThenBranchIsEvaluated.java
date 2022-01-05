@Test public void whenContextPropertyIsSet_IfThenBranchIsEvaluated() throws JoranException {
  context.putProperty(ki1,val1);
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "if0.xml");
  verifyConfig(new String[]{"BEGIN","a","END"});
}
