@Test public void createValidModelContainingHiphen(){
  factory=new GroovyModelFactory(){
    @Override protected InputStream getModelResourceAsStream() throws IOException {
      return getClass().getResourceAsStream("wroWithHiphen.groovy");
    }
  }
;
  final WroModel model=factory.create();
  Assert.assertNotNull(model.getGroupByName("group-with-hiphen"));
}
