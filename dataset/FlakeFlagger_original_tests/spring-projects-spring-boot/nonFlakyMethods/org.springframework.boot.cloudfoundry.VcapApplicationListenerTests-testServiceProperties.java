@Test public void testServiceProperties(){
  EnvironmentTestUtils.addEnvironment(this.context,"VCAP_SERVICES:{\"rds-mysql-n/a\":[{\"name\":\"mysql\",\"label\":\"rds-mysql-n/a\",\"plan\":\"10mb\",\"credentials\":{\"name\":\"d04fb13d27d964c62b267bbba1cffb9da\",\"hostname\":\"mysql-service-public.clqg2e2w3ecf.us-east-1.rds.amazonaws.com\",\"host\":\"mysql-service-public.clqg2e2w3ecf.us-east-1.rds.amazonaws.com\",\"port\":3306,\"user\":\"urpRuqTf8Cpe6\",\"username\":\"urpRuqTf8Cpe6\",\"password\":\"pxLsGVpsC9A5S\"}}]}");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("mysql",this.context.getEnvironment().getProperty("vcap.services.mysql.name"));
}
