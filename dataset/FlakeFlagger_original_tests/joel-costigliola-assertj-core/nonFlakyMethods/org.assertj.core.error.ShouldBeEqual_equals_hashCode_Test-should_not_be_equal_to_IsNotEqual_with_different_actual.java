@Test public void should_not_be_equal_to_IsNotEqual_with_different_actual(){
  assertFalse(factory.equals(shouldBeEqual("Leia","Luke",new StandardRepresentation())));
}
