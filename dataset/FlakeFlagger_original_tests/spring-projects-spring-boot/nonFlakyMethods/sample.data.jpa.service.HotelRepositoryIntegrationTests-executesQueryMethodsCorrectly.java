@Test public void executesQueryMethodsCorrectly(){
  City city=this.cityRepository.findAll(new PageRequest(0,1,Direction.ASC,"name")).getContent().get(0);
  assertThat(city.getName(),is("Atlanta"));
  Page<HotelSummary> hotels=this.repository.findByCity(city,new PageRequest(0,10,Direction.ASC,"name"));
  Hotel hotel=this.repository.findByCityAndName(city,hotels.getContent().get(0).getName());
  assertThat(hotel.getName(),is("Doubletree"));
  List<RatingCount> counts=this.repository.findRatingCounts(hotel);
  assertThat(counts,hasSize(1));
  assertThat(counts.get(0).getRating(),is(Rating.AVERAGE));
  assertThat(counts.get(0).getCount(),is(greaterThan(1L)));
}
