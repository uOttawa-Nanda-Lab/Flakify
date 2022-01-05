@Test public void should_exclude_by_types() throws Exception {
  PropertyTypeExclude exclude=new PropertyTypeExclude(COUNTER,SIMPLE);
  PropertyMeta pm1=PropertyMetaTestBuilder.valueClass(String.class).entityClassName("entity").field("pm1").type(SET).build();
  PropertyMeta pm2=PropertyMetaTestBuilder.valueClass(String.class).entityClassName("entity").field("pm2").type(SIMPLE).build();
  PropertyMeta pm3=PropertyMetaTestBuilder.valueClass(String.class).entityClassName("entity").field("pm3").type(MAP).build();
  assertThat(Collections2.filter(Arrays.asList(pm1,pm2),exclude)).containsOnly(pm1);
  assertThat(Collections2.filter(Arrays.asList(pm1,pm3),exclude)).containsOnly(pm1,pm3);
}
