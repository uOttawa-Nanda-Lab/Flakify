@Test(expected=IndexOutOfBoundsException.class) public void testIllegalSet() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.set(1,"illegal");
}
