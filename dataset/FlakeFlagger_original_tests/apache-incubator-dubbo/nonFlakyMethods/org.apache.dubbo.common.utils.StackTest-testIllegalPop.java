@Test(expected=EmptyStackException.class) public void testIllegalPop() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.pop();
}
