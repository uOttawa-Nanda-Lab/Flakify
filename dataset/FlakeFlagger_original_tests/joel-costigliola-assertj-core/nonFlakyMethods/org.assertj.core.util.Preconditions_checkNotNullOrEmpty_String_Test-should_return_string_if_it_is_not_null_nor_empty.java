@Test public void should_return_string_if_it_is_not_null_nor_empty(){
  String string="a";
  String result=Preconditions.checkNotNullOrEmpty(string);
  assertThat(result).isEqualTo(string);
}
