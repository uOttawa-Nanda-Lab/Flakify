@Test public void whenContextPropertyIsSet_IfThenBranchIsEvaluated_NO_ELSE_DEFINED() throws JoranException {
  context.putProperty(ki1,val1);
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "ifWithoutElse.xml");
  verifyConfig(new String[]{"BEGIN","a","END"});
}
