@Test public void testThatInjectorAccessibleFromNinjaTestIsTheApplicationInjector(){
  Injector injector=getInjector();
  long serviceInitializationTime=injector.getInstance(GreetingService.class).getServiceInitializationTime();
  String serviceInitTimeResult=ninjaTestBrowser.makeJsonRequest(getServerAddress() + "/serviceInitTime");
  assertEquals("{\"initTime\":" + serviceInitializationTime + "}",serviceInitTimeResult);
}
