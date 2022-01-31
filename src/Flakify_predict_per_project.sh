#Flaky Test Classification per-project_validation

dataset="../dataset/FlakeFlagger_dataset.csv" #run Flakify on FlakeFlagger_dataset
#dataset="../dataset/IDoFT_dataset.csv"  #uncomment this to run Flakify on IDoFT dataset
model_weights="../results/model_weights.pt"
results_output="../results/"


python3 Flakify_predictor_per_project.py $dataset $model_weights $results_output