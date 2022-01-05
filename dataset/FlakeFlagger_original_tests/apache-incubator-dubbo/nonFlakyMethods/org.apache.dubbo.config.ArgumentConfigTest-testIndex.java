@Test public void testIndex() throws Exception {
  ArgumentConfig argument=new ArgumentConfig();
  argument.setIndex(1);
  assertThat(argument.getIndex(),is(1));
}
