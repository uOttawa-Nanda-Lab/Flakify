@Test public void testCreateInstanceCustomIdType(){
  Colors id=Colors.GREEN;
  long longProp1=1L;
  ColumnSliceMockImpl slice=new ColumnSliceMockImpl();
  slice.add("lp1",LongSerializer.get().toBytes(longProp1));
  CFMappingDef<MyCustomIdBean,Colors> cfMapDef=cacheMgr.getCfMapDef(MyCustomIdBean.class,true);
  MyCustomIdBean obj=new HectorObjectMapper(cacheMgr).createObject(cfMapDef,id,slice);
  assertEquals(id,obj.getId());
  assertEquals(longProp1,obj.getLongProp1());
}
