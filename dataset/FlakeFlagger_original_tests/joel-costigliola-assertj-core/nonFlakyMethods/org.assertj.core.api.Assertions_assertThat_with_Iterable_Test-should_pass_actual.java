@Test public void should_pass_actual(){
  Iterable<String> names=newLinkedHashSet("Luke");
  AbstractIterableAssert<?,? extends Iterable<? extends String>,String> assertions=Assertions.assertThat(names);
  assertSame(names,assertions.actual);
}
