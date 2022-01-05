@Test public void shouldCreateEmptyModelWhenValidationDisabledAndXmlIsNotValid(){
  factory=new XmlModelFactory(){
    @Override protected InputStream getModelResourceAsStream(){
      return new ByteArrayInputStream("<xml></xml>".getBytes());
    }
  }
.setValidateXml(false);
  WroTestUtils.init(factory);
  assertEquals(new WroModel(),factory.create());
}
