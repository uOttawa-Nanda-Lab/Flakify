@Test public void testDoublePadding(){
  TwoWayKey2StringMapper mapper=new IndexedKeyFormatMapper(createPersistenceList(17));
  Assert.assertTrue(mapper.isSupportedType(Type.TYPE00.getClass()));
  Assert.assertTrue(mapper.isSupportedType(Type.TYPE15.getClass()));
  Assert.assertTrue(mapper.isSupportedType(Type.TYPE16.getClass()));
  Assert.assertFalse(mapper.isSupportedType(Type.TYPE17.getClass()));
  String result=mapper.getStringMapping(Type.TYPE00);
  Assert.assertSame(Type.TYPE00,mapper.getKeyMapping(result));
  Assert.assertEquals("00TYPE00",result);
  result=mapper.getStringMapping(Type.TYPE15);
  Assert.assertSame(Type.TYPE15,mapper.getKeyMapping(result));
  Assert.assertEquals("0FTYPE15",result);
  result=mapper.getStringMapping(Type.TYPE16);
  Assert.assertSame(Type.TYPE16,mapper.getKeyMapping(result));
  Assert.assertEquals("10TYPE16",result);
}
