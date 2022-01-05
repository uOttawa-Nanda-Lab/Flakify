@Test public void testInheritanceWithMultiLevels(){
  ClassCacheMgr cacheMgr=new ClassCacheMgr();
  CFMappingDef<Desk,String> cfMapDef=cacheMgr.initializeCacheForClass(Desk.class);
  CFMappingDef<Furniture,String> cfBaseMapDef=cacheMgr.getCfMapDef(Furniture.class,true);
  assertEquals(5,cfMapDef.getAllProperties().size());
  assertNotNull(cfMapDef.getCfSuperMapDef());
  assertNotNull(cfMapDef.getCfBaseMapDef());
  assertEquals(Desk.class.getSuperclass(),cfMapDef.getCfSuperMapDef().getClazz());
  assertEquals(Desk.class.getSuperclass().getSuperclass(),cfMapDef.getCfSuperMapDef().getCfSuperMapDef().getClazz());
  assertEquals(cfBaseMapDef.getColFamName(),cfMapDef.getColFamName());
  assertEquals("type",cfMapDef.getDiscColumn());
  assertEquals("table_desk",cfMapDef.getDiscValue());
  assertEquals(DiscriminatorType.STRING,cfMapDef.getDiscType());
  assertEquals("id",cfMapDef.getIdPropertyDef().getPropDesc().getName());
}
