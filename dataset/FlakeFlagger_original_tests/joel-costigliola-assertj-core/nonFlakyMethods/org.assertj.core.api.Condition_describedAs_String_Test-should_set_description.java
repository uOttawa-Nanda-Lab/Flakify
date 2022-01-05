@Test public void should_set_description(){
  String description=someTextDescription();
  condition.describedAs(description);
  assertEquals(description,condition.description.value());
}
