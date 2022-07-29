#Flakify's flaky test prediction using per-project validation
dataset=$1
data_path="../dataset"
results_path="../results"
dataset_file="${data_path}/${dataset}/Flakify_${dataset}_dataset.csv" 
model_weights="${results_path}/Flakify_per_project_model_weights_on_${dataset}_dataset.pt"
results_file="${results_path}/Flakify_cross_validation_results_on_${dataset}_dataset.csv"

python3 Flakify_predictor_per_project.py $dataset_file $model_weights $results_file