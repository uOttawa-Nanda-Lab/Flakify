@Test public void testClear() throws Exception {
  Stack<String> stack=new Stack<String>();
  stack.push("one");
  stack.push("two");
  assertThat(stack.isEmpty(),is(false));
  stack.clear();
  assertThat(stack.isEmpty(),is(true));
}
