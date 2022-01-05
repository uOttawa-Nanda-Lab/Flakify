@Test public void testGetKeyPropertyId(){
  Schema schema=new SchemaImpl(resourceProvider,propertyProviders);
  Assert.assertEquals(PropertyHelper.getPropertyId("c1","p1"),schema.getKeyPropertyId(Resource.Type.Cluster));
  Assert.assertEquals(PropertyHelper.getPropertyId("c1","p2"),schema.getKeyPropertyId(Resource.Type.Host));
  Assert.assertEquals(PropertyHelper.getPropertyId("c1","p3"),schema.getKeyPropertyId(Resource.Type.Component));
}
