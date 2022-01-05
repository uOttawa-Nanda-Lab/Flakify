@Test public void shouldExtractGroupWhenUrlContainsEncodedSession(){
  final HttpServletRequest request=mockRequestForUri("/contextPath/wro/all.js;jsessionID=A327EBE59831FF690C26B0B895EA877E");
  assertEquals("all",groupExtractor.getGroupName(request));
  assertEquals(ResourceType.JS,groupExtractor.getResourceType(request));
}
