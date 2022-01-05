@Test public void customDateFormatClass() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.date-format:org.springframework.boot.autoconfigure.jackson.JacksonAutoConfigurationTests.MyDateFormat");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  Date date=new DateTime(1988,6,25,20,30).toDate();
  assertEquals("\"1988-06-25 20:30:00\"",mapper.writeValueAsString(date));
}
