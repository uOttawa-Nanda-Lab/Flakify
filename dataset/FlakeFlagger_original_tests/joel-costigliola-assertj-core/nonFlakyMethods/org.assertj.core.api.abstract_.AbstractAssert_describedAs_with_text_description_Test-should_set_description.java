@Test public void should_set_description(){
  assertions.describedAs(description);
  assertEquals(description,assertions.descriptionText());
}
