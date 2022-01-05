@Test(expected=IndexOutOfBoundsException.class) public void testIllegalRemove() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.remove(1);
}
