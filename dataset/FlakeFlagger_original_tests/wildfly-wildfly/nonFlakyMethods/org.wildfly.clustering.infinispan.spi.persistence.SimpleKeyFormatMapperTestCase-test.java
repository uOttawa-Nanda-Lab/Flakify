@Test public void test(){
  KeyFormat<Object> keyFormat=mock(KeyFormat.class);
  TwoWayKey2StringMapper mapper=new SimpleKeyFormatMapper(keyFormat);
  Object key=new Object();
  String formatted="foo";
  when(keyFormat.getTargetClass()).thenReturn(Object.class);
  when(keyFormat.format(key)).thenReturn(formatted);
  when(keyFormat.parse(formatted)).thenReturn(key);
  Assert.assertSame(formatted,mapper.getStringMapping(key));
  Assert.assertSame(key,mapper.getKeyMapping(formatted));
  Assert.assertTrue(mapper.isSupportedType(Object.class));
  Assert.assertFalse(mapper.isSupportedType(Integer.class));
}
