@Test public void should_create_a_new_Properties(){
  Properties<String> properties=Properties.extractProperty("id").ofType(String.class);
  assertEquals("id",properties.propertyName);
  assertEquals(String.class,properties.propertyType);
}
