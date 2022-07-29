# Test Flakiness Classification 
version=$1
data_path="../dataset/"
results_path="../results/"
main_processed_data="$data_path/FlakeFlagger/FlakeFlagger_filtered_dataset.csv"
FlakeFlaggerFeatures="$data_path/FlakeFlagger/FlakeFlaggerFeaturesTypes.csv"
InformationGain="$data_path/FlakeFlagger/Information_gain_per_feature.csv"

python3 FlakeFlagger_predictor.py $main_processed_data $FlakeFlaggerFeatures $InformationGain $results_path $version
