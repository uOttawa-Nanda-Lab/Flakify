@Test(expected=EmptyStackException.class) public void testIllegalPeek() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.peek();
}
