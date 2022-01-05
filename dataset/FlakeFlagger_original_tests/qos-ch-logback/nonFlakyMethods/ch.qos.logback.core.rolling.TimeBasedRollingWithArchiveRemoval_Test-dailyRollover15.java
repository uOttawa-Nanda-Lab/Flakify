@Test public void dailyRollover15(){
  int maxHistory=5;
  int simulatedNumberOfPeriods=15;
  int startInactivity=6;
  int numInactivityPeriods=3;
  generateDailyRollover(currentTime,maxHistory,simulatedNumberOfPeriods,startInactivity,numInactivityPeriods);
}
