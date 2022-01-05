@Test public void testInheritanceOfEntity(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  CFMappingDef<MyRedTestBean,String> cfMapDef=cacheMgr.initializeCacheForClass(MyRedTestBean.class);
  assertEquals(13,cfMapDef.getAllProperties().size());
  assertNotNull(cfMapDef.getCfBaseMapDef());
  assertEquals(MyRedTestBean.class,cfMapDef.getClazz());
  assertEquals("TestBeanColumnFamily",cfMapDef.getColFamName());
  assertEquals("myType",cfMapDef.getDiscColumn());
  assertEquals(DiscriminatorType.STRING,cfMapDef.getDiscType());
  assertEquals("baseId",cfMapDef.getIdPropertyDef().getPropDesc().getName());
}
