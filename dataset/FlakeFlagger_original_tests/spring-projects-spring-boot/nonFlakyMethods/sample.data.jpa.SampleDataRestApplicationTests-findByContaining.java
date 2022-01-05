@Test public void findByContaining() throws Exception {
  this.mvc.perform(get("/api/cities/search/findByNameContainingAndCountryContainingAllIgnoringCase?name=&country=UK")).andExpect(status().isOk()).andExpect(jsonPath("_embedded.citys",hasSize(3)));
}
