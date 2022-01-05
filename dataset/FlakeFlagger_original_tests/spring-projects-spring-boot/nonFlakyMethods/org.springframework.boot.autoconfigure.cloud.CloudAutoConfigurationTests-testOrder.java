@Test public void testOrder() throws Exception {
  ResourceLoader loader=new DefaultResourceLoader();
  TestAutoConfigurationSorter sorter=new TestAutoConfigurationSorter(loader);
  Collection<String> classNames=new ArrayList<String>();
  classNames.add(MongoAutoConfiguration.class.getName());
  classNames.add(DataSourceAutoConfiguration.class.getName());
  classNames.add(MongoRepositoriesAutoConfiguration.class.getName());
  classNames.add(JpaRepositoriesAutoConfiguration.class.getName());
  classNames.add(CloudAutoConfiguration.class.getName());
  List<String> ordered=sorter.getInPriorityOrder(classNames);
  assertThat(ordered.get(0),equalTo(CloudAutoConfiguration.class.getName()));
}
