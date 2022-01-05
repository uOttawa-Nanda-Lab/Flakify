@Test public void testGetStringArrayWorks(){
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
  assertEquals("one",ninjaProperties.get("getOneElementStringArray"));
  assertEquals("one",ninjaProperties.getStringArray("getOneElementStringArray")[0]);
  assertEquals("one , me",ninjaProperties.get("getMultipleElementStringArrayWithSpaces"));
  assertEquals("one",ninjaProperties.getStringArray("getMultipleElementStringArrayWithSpaces")[0]);
  assertEquals("me",ninjaProperties.getStringArray("getMultipleElementStringArrayWithSpaces")[1]);
  assertEquals("one,me",ninjaProperties.get("getMultipleElementStringArrayWithoutSpaces"));
  assertEquals("one",ninjaProperties.getStringArray("getMultipleElementStringArrayWithoutSpaces")[0]);
  assertEquals("me",ninjaProperties.getStringArray("getMultipleElementStringArrayWithoutSpaces")[1]);
}
