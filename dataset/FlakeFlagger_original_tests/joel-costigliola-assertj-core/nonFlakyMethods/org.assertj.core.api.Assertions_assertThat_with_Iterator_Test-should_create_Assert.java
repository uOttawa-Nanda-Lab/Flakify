@Test public void should_create_Assert(){
  AbstractIterableAssert<?,? extends Iterable<? extends Object>,Object> assertions=Assertions.assertThat(newLinkedHashSet());
  assertNotNull(assertions);
}
