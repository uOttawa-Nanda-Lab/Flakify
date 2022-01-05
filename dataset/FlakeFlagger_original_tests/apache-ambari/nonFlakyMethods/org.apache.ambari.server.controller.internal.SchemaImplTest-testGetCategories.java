@Test public void testGetCategories(){
  Schema schema=new SchemaImpl(resourceProvider,propertyProviders);
  Map<String,Set<String>> categories=schema.getCategoryProperties();
  Assert.assertEquals(4,categories.size());
  Assert.assertTrue(categories.containsKey("c1"));
  Assert.assertTrue(categories.containsKey("c2"));
  Assert.assertTrue(categories.containsKey("c3"));
  Assert.assertTrue(categories.containsKey("c4"));
  Set<String> properties=categories.get("c1");
  Assert.assertEquals(3,properties.size());
  Assert.assertTrue(properties.contains("p1"));
  Assert.assertTrue(properties.contains("p2"));
  Assert.assertTrue(properties.contains("p3"));
  properties=categories.get("c2");
  Assert.assertEquals(1,properties.size());
  Assert.assertTrue(properties.contains("p4"));
  properties=categories.get("c3");
  Assert.assertEquals(2,properties.size());
  Assert.assertTrue(properties.contains("p5"));
  Assert.assertTrue(properties.contains("p6"));
  properties=categories.get("c4");
  Assert.assertEquals(2,properties.size());
  Assert.assertTrue(properties.contains("p7"));
  Assert.assertTrue(properties.contains("p8"));
}
