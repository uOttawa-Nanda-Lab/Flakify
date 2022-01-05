@Test public void testIsNotSerializable(){
  assertFalse(HectorObjectMapper.isSerializable(HectorObjectMapper.class));
}
