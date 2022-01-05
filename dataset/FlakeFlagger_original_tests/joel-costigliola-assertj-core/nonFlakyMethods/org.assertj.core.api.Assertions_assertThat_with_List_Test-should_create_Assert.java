@Test public void should_create_Assert(){
  AbstractListAssert<?,? extends List<? extends Object>,Object> assertions=Assertions.assertThat(emptyList());
  assertNotNull(assertions);
}
