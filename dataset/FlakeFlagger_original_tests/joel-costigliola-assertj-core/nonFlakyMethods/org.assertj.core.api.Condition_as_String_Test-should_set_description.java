@Test public void should_set_description(){
  String description=someTextDescription();
  condition.as(description);
  assertEquals(description,condition.description.value());
}
