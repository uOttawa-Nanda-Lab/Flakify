# Test Smell Detector and Retainer

This is a step-by-step guideline to detect and retain test smells in the source code of test cases.

### Requirements
- Eclipse IDE (the version we used was 2021-09)
- The libraries (the _.jar_ files in the `lib\` directory) 

### Input Files:
This is a list of input files that are required to accomplish this step:
* dataset/test_cases_full_code/
* dataset/FlakeFlagger_filtered_dataset.csv

The `dataset/FlakeFlagger_filtered_dataset.csv` is just used to obtain the label (flaky=1, non-flaky=0) and project name for each test case in `dataset/test_cases_full_code/`.

### Output Files:
* dataset/test_cases_preprocessed_code/
* dataset/Flakify_dataset.csv

### Replicating the experiment
To detect test smells and retain only code statements related to them, the `src/TestSmellsDetector.java` file should be compiled and run using the Eclipse IDE by having all the _.jar_ files in the class path. Each test case in the `dataset/test_cases_full_code/` directory is then parsed to generate a corresponding pre-processed Java file in the `dataset/test_cases_preprocessed_code/` directory. The original and pre-processed source code of all test cases are also saved in the `dataset/Flakify_dataset.csv` file, along with the projects they belong to as well as their labels.

---

# Flakify Replication

This is the guideline for replicating the experiments we used to evaluate Flakify for classifying test cases as flaky and non-flaky using both cross vlidation and per-project validation.

### Requirements
This is a list of all required python packages:
- python =3.8.5
- imbalanced_learn= 0.8.1
- numpy= 1.19.5
- pandas= 1.3.3
- transformer= 4.10.2
- torch=1.5.0
- scikit_learn= 0.22.1

### Input Files:
This is a list of input files that are required to accomplish this step:
* dataset/Flakify_dataset.csv
* dataset/IDoFT_dataset.csv
This file contains the full code and pre-processed code of the test cases in both FlakeFlagger and IDOFT dataset, along with their ground truth labels (_flaky_ and _non-flaky_).

### Output File:
* results/Flakify_results.csv 
* results/per_project_results.csv 

### Replicating Flakify experiments
To run the Flakify experiment, navigate to `src\` folder and run the following commands for cross validation and per-project validation respectively:

```console
bash Flakify_predict.sh
```

This will generate the classification results into `results/Flakify_results.csv` for the whole cross validation experiment. 

```console
bash Flakify_predict_per_project.sh
```

This will generate the classification results into `results/per_project_results.csv` for the whole per-project validation experiment. 

`results/Flakify_Model.pt` contains the saved model of Flakify on FlakeFlagger Dataset. 

`results/IDOFT_Model.pt` contains the saved model of Flakify on FlakeFlagger Dataset. 

---

# FlakeFlagger Replication

This is the guideline for replicating the experiments we used to evaluate the two versions of FlakeFlagger, white-box and black-box, for classifying test cases as flaky and non-flaky.

### Requirements
This is a list of all required python packages:
- python =3.8.5
- imbalanced_learn= 0.8.1
- pandas= 1.3.3
- scikit_learn= 0.22.1

### Input File:
This is a list of input files that are required to accomplish this step:
* dataset/FlakeFlagger_filtered_dataset.csv
* dataset/FlakeFlaggerFeaturesTypes.csv
* dataset/Information_gain_per_feature.csv

### Output File:
* results/FlakeFlagger_black-box_results.csv
* results/FlakeFlagger_white-box_results.csv

### Replicating FlakeFlagger experiments
To run the FlakeFlagger experiments, navigate to `src\` folder and run the following command:

```console
bash FlakeFlagger_predict.sh
```

This will generate the classification results into `results/FlakeFlagger_white-box_results.csv` and `results/FlakeFlagger_black-box_results.csv` for the both white-box and black-box experiments, respectively.
