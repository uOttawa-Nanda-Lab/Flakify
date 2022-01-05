@Test public void includeNestedWithinIf() throws JoranException {
  context.putProperty(THEN_FILE_TO_INCLUDE_KEY,THEN_FILE_TO_INCLUDE);
  context.putProperty(ELSE_FILE_TO_INCLUDE_KEY,ELSE_FILE_TO_INCLUDE);
  tc.doConfigure(NESTED_INCLUDE_FILE);
  verifyConfig(new String[]{"BEGIN","e0","IncludedB0","e1","END"});
}
