@Test public void should_allow_null(){
  AbstractIterableAssert<?,? extends Iterable<? extends String>,String> assertions=assertThat((Iterator<String>)null);
  assertThat(assertions.actual).isNull();
}
