@Test public void should_return_null_if_Collection_is_null(){
  assertThat(Collections.format(new StandardRepresentation(),null)).isNull();
  assertThat(Collections.format(null)).isNull();
}
