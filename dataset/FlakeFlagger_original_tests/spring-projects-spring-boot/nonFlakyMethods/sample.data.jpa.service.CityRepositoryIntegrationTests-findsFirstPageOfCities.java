@Test public void findsFirstPageOfCities(){
  Page<City> cities=this.repository.findAll(new PageRequest(0,10));
  assertThat(cities.getTotalElements(),is(greaterThan(20L)));
}
