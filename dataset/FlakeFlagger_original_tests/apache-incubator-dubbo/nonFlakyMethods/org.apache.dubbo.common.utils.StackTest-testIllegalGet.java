@Test(expected=IndexOutOfBoundsException.class) public void testIllegalGet() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.get(1);
}
