@Test public void should_format_boolean_array(){
  assertEquals("[true, false, true]",formatter.format(new StandardRepresentation(),new boolean[]{true,false,true}));
}
