#Flaky Test Classification 

dataset="../dataset/Flakify_dataset.csv"
model_weights="../results/model_weights.pt"
results_output="../results/"


python3 Flakify_predictor.py $dataset $model_weights $results_output