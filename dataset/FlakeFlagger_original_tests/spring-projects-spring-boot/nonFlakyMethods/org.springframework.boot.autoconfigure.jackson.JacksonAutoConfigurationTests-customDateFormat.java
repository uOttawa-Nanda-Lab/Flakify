@Test public void customDateFormat() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.date-format:yyyyMMddHHmmss");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  Date date=new DateTime(1988,6,25,20,30).toDate();
  assertEquals("\"19880625203000\"",mapper.writeValueAsString(date));
}
