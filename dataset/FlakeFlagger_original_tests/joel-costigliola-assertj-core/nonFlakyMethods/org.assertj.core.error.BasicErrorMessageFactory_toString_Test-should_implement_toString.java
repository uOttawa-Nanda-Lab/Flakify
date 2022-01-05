@Test public void should_implement_toString(){
  assertEquals("BasicErrorMessageFactory[format='Hello %s', arguments=[\"Yoda\"]]",factory.toString());
}
