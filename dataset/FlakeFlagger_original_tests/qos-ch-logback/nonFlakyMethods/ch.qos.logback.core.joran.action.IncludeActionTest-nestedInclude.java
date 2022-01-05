@Test public void nestedInclude() throws JoranException {
  System.setProperty(SUB_FILE_KEY,SUB_FILE);
  System.setProperty(INCLUDE_KEY,INTERMEDIARY_FILE);
  tc.doConfigure(TOP_BY_FILE);
  Stack<String> witness=new Stack<String>();
  witness.push("a");
  witness.push("b");
  witness.push("c");
  assertEquals(witness,stackAction.getStack());
}
