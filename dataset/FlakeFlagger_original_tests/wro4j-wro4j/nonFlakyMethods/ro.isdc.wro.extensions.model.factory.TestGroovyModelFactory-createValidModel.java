@Test public void createValidModel(){
  factory=new GroovyModelFactory(){
    @Override protected InputStream getModelResourceAsStream() throws IOException {
      return TestGroovyModelFactory.class.getResourceAsStream("wro.groovy");
    }
  }
;
  final WroModel model=factory.create();
  Assert.assertNotNull(model);
  Assert.assertEquals(Arrays.asList("g1","g2"),new WroModelInspector(model).getGroupNames());
  Assert.assertEquals(2,model.getGroupByName("g1").getResources().size());
  Assert.assertTrue(model.getGroupByName("g1").getResources().get(0).isMinimize());
  Assert.assertEquals("/static/app.js",model.getGroupByName("g1").getResources().get(0).getUri());
  Assert.assertEquals(ResourceType.JS,model.getGroupByName("g1").getResources().get(0).getType());
  Assert.assertTrue(model.getGroupByName("g1").getResources().get(1).isMinimize());
  Assert.assertEquals("/static/app.css",model.getGroupByName("g1").getResources().get(1).getUri());
  Assert.assertEquals(ResourceType.CSS,model.getGroupByName("g1").getResources().get(1).getType());
  Assert.assertEquals(2,model.getGroupByName("g2").getResources().size());
  Assert.assertFalse(model.getGroupByName("g2").getResources().get(1).isMinimize());
  LOG.debug("model: ",model);
}
