@Test public void testAddParameters() throws Exception {
  URL url=URL.valueOf("dubbo://127.0.0.1:20880");
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("version",null);
  url.addParameters(parameters);
}
