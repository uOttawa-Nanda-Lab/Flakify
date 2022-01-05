public void testGetMBeanInfo(){
  MBeanInfo info=this.stats.getMBeanInfo();
  MBeanAttributeInfo[] attributes=info.getAttributes();
  assertEquals(6,attributes.length);
  Map<String,MBeanAttributeInfo> attributeByName=new HashMap<String,MBeanAttributeInfo>(attributes.length);
  for (  MBeanAttributeInfo attr : attributes)   attributeByName.put(attr.getName(),attr);
  assertAttribute(attributeByName.get("metricsRate"),"metricsRate","java.lang.Float","test");
  assertAttribute(attributeByName.get("intValue"),"intValue","java.lang.Integer","test");
  assertAttribute(attributeByName.get("varyRateMinTime"),"varyRateMinTime","java.lang.Long","test");
  assertAttribute(attributeByName.get("varyRateMaxTime"),"varyRateMaxTime","java.lang.Long","test");
  assertAttribute(attributeByName.get("varyRateAvgTime"),"varyRateAvgTime","java.lang.Long","test");
  assertAttribute(attributeByName.get("varyRateNumOps"),"varyRateNumOps","java.lang.Integer","test");
}
