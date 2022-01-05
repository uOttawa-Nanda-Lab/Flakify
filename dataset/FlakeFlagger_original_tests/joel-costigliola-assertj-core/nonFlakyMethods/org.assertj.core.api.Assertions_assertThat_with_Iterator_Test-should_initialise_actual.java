@Test public void should_initialise_actual(){
  Iterator<String> names=asList("Luke","Leia").iterator();
  AbstractIterableAssert<?,? extends Iterable<? extends String>,String> assertions=assertThat(names);
  assertThat(assertions.actual).containsOnly("Leia","Luke");
}
