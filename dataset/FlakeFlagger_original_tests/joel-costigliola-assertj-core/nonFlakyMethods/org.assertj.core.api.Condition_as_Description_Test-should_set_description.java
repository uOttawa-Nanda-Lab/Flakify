@Test public void should_set_description(){
  condition.as(description);
  assertSame(description,condition.description());
}
