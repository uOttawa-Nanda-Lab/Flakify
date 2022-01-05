@Test public void should_format_Collection(){
  List<? extends Object> list=asList("First",3);
  assertThat(Collections.format(new StandardRepresentation(),list)).isEqualTo("[\"First\", 3]");
  assertThat(Collections.format(list)).isEqualTo("[\"First\", 3]");
  assertThat(Collections.format(new StandardRepresentation(),list,"(",")")).isEqualTo("(\"First\", 3)");
  assertThat(Collections.format(list,"(",")")).isEqualTo("(\"First\", 3)");
}
