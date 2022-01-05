@Test public void testSinglePadding(){
  TwoWayKey2StringMapper mapper=new IndexedKeyFormatMapper(createPersistenceList(16));
  Assert.assertTrue(mapper.isSupportedType(Type.TYPE00.getClass()));
  Assert.assertTrue(mapper.isSupportedType(Type.TYPE15.getClass()));
  Assert.assertFalse(mapper.isSupportedType(Type.TYPE16.getClass()));
  Assert.assertFalse(mapper.isSupportedType(Type.TYPE17.getClass()));
  String result=mapper.getStringMapping(Type.TYPE00);
  Assert.assertSame(Type.TYPE00,mapper.getKeyMapping(result));
  Assert.assertEquals("0TYPE00",result);
  result=mapper.getStringMapping(Type.TYPE15);
  Assert.assertSame(Type.TYPE15,mapper.getKeyMapping(result));
  Assert.assertEquals("FTYPE15",result);
}
