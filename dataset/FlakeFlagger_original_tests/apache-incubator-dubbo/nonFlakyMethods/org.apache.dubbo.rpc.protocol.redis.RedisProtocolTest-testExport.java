@Test(expected=UnsupportedOperationException.class) public void testExport(){
  protocol.export(protocol.refer(IDemoService.class,registryUrl));
}
