@Test public void should_implement_toString(){
  assertThat(unquotedString("some value").toString()).isEqualTo("some value");
}
