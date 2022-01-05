@Test public void getType(){
  OptionMap options=OptionMap.builder().getMap();
  assertSame(Connector.Type.AJP,new UndertowConnector(new AjpListenerService("","",options,OptionMap.EMPTY)).getType());
  assertSame(Connector.Type.HTTP,new UndertowConnector(new HttpListenerService("","",options,OptionMap.EMPTY,false,false,false)).getType());
  assertSame(Connector.Type.HTTPS,new UndertowConnector(new HttpsListenerService("","",options,null,OptionMap.EMPTY,false)).getType());
}
