@Test public void should_set_description(){
  assertions.as(d);
  assertEquals(d.value(),assertions.descriptionText());
}
