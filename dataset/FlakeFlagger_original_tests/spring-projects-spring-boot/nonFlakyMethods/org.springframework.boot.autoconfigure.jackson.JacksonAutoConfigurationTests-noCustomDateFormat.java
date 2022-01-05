@Test public void noCustomDateFormat() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  Date date=new DateTime(1988,6,25,20,30).toDate();
  assertEquals(String.valueOf(date.getTime()),mapper.writeValueAsString(date));
}
