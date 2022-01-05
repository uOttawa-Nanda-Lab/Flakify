@Test public void should_return_empty_brackets_if_Collection_is_empty(){
  assertThat(Collections.format(new StandardRepresentation(),new ArrayList<String>())).isEqualTo("[]");
  assertThat(Collections.format(new ArrayList<String>())).isEqualTo("[]");
  assertThat(Collections.format(new StandardRepresentation(),new ArrayList<String>(),">","<")).isEqualTo("><");
  assertThat(Collections.format(new ArrayList<String>(),">","<")).isEqualTo("><");
}
