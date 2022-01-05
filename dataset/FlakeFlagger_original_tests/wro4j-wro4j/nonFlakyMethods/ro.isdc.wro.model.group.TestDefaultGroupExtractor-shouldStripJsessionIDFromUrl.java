@Test public void shouldStripJsessionIDFromUrl(){
  final HttpServletRequest request=mockRequestForUri("https://www.servername.com:80/js/all.js;jsessionID=A327EBE59831FF690C26B0B895EA877EEFKDD&param.with.dot=value");
  assertEquals("all",groupExtractor.getGroupName(request));
  assertEquals(ResourceType.JS,groupExtractor.getResourceType(request));
}
