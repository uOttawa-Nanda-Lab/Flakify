@Test public void testMapToInterface() throws Exception {
  Map map=new HashMap();
  map.put("content","greeting");
  map.put("from","dubbo");
  map.put("urgent",true);
  Object o=PojoUtils.realize(map,Message.class);
  Message message=(Message)o;
  assertThat(message.getContent(),equalTo("greeting"));
  assertThat(message.getFrom(),equalTo("dubbo"));
  assertTrue(message.isUrgent());
}
