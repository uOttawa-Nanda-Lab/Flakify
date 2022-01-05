@Test public void testToString(){
  Statistics statistics=new Statistics(new URL("dubbo","10.20.153.10",0));
  statistics.setApplication("demo");
  statistics.setMethod("findPerson");
  statistics.setServer("10.20.153.10");
  statistics.setGroup("unit-test");
  statistics.setService("MemberService");
  assertThat(statistics.toString(),is("dubbo://10.20.153.10"));
  Statistics statisticsWithDetailInfo=new Statistics(new URL("dubbo","10.20.153.10",0).addParameter(MonitorService.APPLICATION,"morgan").addParameter(MonitorService.INTERFACE,"MemberService").addParameter(MonitorService.METHOD,"findPerson").addParameter(MonitorService.CONSUMER,"10.20.153.11").addParameter(MonitorService.GROUP,"unit-test").addParameter(MonitorService.SUCCESS,1).addParameter(MonitorService.FAILURE,0).addParameter(MonitorService.ELAPSED,3).addParameter(MonitorService.MAX_ELAPSED,3).addParameter(MonitorService.CONCURRENT,1).addParameter(MonitorService.MAX_CONCURRENT,1));
  Assert.assertThat(statisticsWithDetailInfo.getServer(),equalTo(statistics.getServer()));
  Assert.assertThat(statisticsWithDetailInfo.getService(),equalTo(statistics.getService()));
  Assert.assertThat(statisticsWithDetailInfo.getMethod(),equalTo(statistics.getMethod()));
  Assert.assertThat(statisticsWithDetailInfo.getGroup(),equalTo(statistics.getGroup()));
  Assert.assertThat(statisticsWithDetailInfo,not(equalTo(statistics)));
}
