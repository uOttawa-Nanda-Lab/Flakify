@Test public void testCallback() throws Exception {
  ArgumentConfig argument=new ArgumentConfig();
  argument.setCallback(true);
  assertThat(argument.isCallback(),is(true));
}
