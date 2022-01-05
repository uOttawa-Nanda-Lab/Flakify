# Test Flakiness Classification 

main_processed_data="../dataset/FlakeFlagger_filtered_dataset.csv"
FlakeFlaggerFeatures="../dataset/FlakeFlaggerFeaturesTypes.csv"
InformationGain="../dataset/Information_gain_per_feature.csv"
results_output="../results/"

python3 FlakeFlagger_predictor.py $main_processed_data $FlakeFlaggerFeatures $InformationGain $results_output "white-box"
python3 FlakeFlagger_predictor.py $main_processed_data $FlakeFlaggerFeatures $InformationGain $results_output "black-box"
