@Test public void should_set_description(){
  condition.describedAs(description);
  assertSame(description,condition.description());
}
