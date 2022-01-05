@Test public void should_create_Assert_with_StringBuilder(){
  AbstractCharSequenceAssert<?,?> assertions=Assertions.assertThat(new StringBuilder("Yoda"));
  assertNotNull(assertions);
}
