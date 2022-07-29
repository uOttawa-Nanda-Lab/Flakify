This is the replication package associated with the paper: _Flakify: A Black-Box, Language Model-based Predictor for Flaky Tests_. We explain how to use it to reproduce the results reported in the paper. A presistent version of this replication package was made available on Zenodo [https://zenodo.org/record/6994692](https://zenodo.org/record/6994692).


# Flakify Test Smell Detector

This is a step-by-step guideline to detect test smells in the source code of test cases and retain statements that match them.

### Requirements
- Eclipse IDE (the version we used was 2021-12)
- The libraries (the _.jar_ files in the `lib\` directory) 

### Input Files:
This is a list of input files that are required to accomplish this step:
* dataset/FlakeFlagger/FlakeFlagger_filtered_dataset.csv
* dataset/FlakeFlagger/FlakeFlagger_class_files/

* dataset/IDoFT/IDoFT_filtered_dataset.csv
* dataset/IDoFT/IDoFT_class_files/

The `dataset/FlakeFlagger/FlakeFlagger_filtered_dataset.csv` and `dataset/IDoFT/IDoFT_filtered_dataset.csv` are used to obtain the label (_flaky_=1 or _non-flaky_=0) and project name for each test case parsed from  `dataset/FlakeFlagger/FlakeFlagger_class_files/` and `dataset/IDoFT/IDoFT_class_files/`, respectively.

### Output Files:
* dataset/FlakeFlagger/FlakeFlagger_dataset.csv
* dataset/FlakeFlagger/FlakeFlagger_test_cases_full_code/
* dataset/FlakeFlagger/FlakeFlagger_test_cases_preprocessed_code/

* dataset/IDoFT/IDoFT_dataset.csv
* dataset/IDoFT/IDoFT_test_cases_full_code/
* dataset/IDoFT/IDoFT_test_cases_preprocessed_code/

### Replicating the experiment
To detect test smells and retain only code statements related to them, the `src/FlakifySmellsDetector.java` file should be compiled and run using the Eclipse IDE by having all the _.jar_ files in the classpath. 

The pre-generated executable Jar file `src/FlakifySmellsDetector.jar` can be executed using the shell script `src/FlakifySmellsDetector.sh` after changing paths for each dataset as needed, using the following commands:

```console
bash FlakifySmellsDetector.sh FlakeFlagger
bash FlakifySmellsDetector.sh IDoFT
```

It will generate the dataset required to run Flakify's flaky test prediction model for the datasets given as input. The class file containing each of the test cases is then parsed to produce the corresponding full code and pre-processed code of the test case. The full and pre-processed source code of all test cases are also combined and saved in a CSV file, along with test smells found, project names, and labels.

---

# Flakify Replication

This is the guideline for replicating the experiments we used to evaluate Flakify for classifying test cases as _flaky_ and _non-flaky_ using both cross validation and per-project validation.

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
* dataset/FlakeFlagger/Flakify_FlakeFlagger_dataset.csv
* dataset/IDoFT/Flakify_IDoFT_dataset.csv

This file contains the full code and pre-processed code of the test cases in both the FlakeFlagger and IDOFT datasets, along with their ground truth labels (_flaky_ and _non-flaky_).

### Output File:
* results/Flakify_cross_validation_results_on_FlakeFlagger_dataset.csv
* results/Flakify_per_project_results_on_FlakeFlagger_dataset.csv
* results/Flakify_model_trained_on_FlakeFlagger_dataset.pt

* results/Flakify_cross_validation_results_on_IDoFT_dataset.csv
* results/Flakify_per_project_results_on_IDoFT_dataset.csv
* results/Flakify_model_trained_on_IDoFT_dataset.pt

### Replicating Flakify experiments

#### Cross-Validation
To run the Flakify experiment using cross-validation on the two datasets, navigate to the `src\` folder and run the following commands:

```console
bash Flakify_predictor_cross_validation.sh FlakeFlagger
bash Flakify_predictor_cross_validation.sh IDoFT
```

This will generate the classification results into `results/Flakify_cross_validation_results_on_FlakeFlagger_dataset.csv` and `results/Flakify_cross_validation_results_on_IDoFT_dataset.csv` for the cross-validation experiments on both datasets. It will also save the weights of the two models trained on the FlakeFlagger and IDoFT datasets into `results/Flakify_model_trained_on_FlakeFlagger_dataset.pt` and `results/Flakify_model_trained_on_IDoFT_dataset.pt`, respectively. 

#### Per-project Validation
To run the Flakify experiment using per-project validation on the two datasets, navigate to the `src\` folder and run the following commands:

```console
bash Flakify_predictor_per_project.sh FlakeFlagger
bash Flakify_predictor_per_project.sh IDoFT
```

This will generate the classification results into `results/Flakify_per_project_results_on_FlakeFlagger_dataset.csv` and `results/Flakify_per_project_results_on_IDoFT_dataset.csv` for the whole per-project validation experiments on both datasets. 

---

# FlakeFlagger Replication

This is the guideline for replicating the experiments we used to evaluate the two versions of FlakeFlagger, white-box and black-box, for classifying test cases as _flaky_ and _non-flaky_ using cross-validation on the FlakeFlagger dataset.

### Requirements
This is a list of all required python packages:
- python =3.8.5
- imbalanced_learn= 0.8.1
- pandas= 1.3.3
- scikit_learn= 0.22.1

### Input File:
This is a list of input files that are required to accomplish this step:
* dataset/FlakeFlagger/FlakeFlagger_filtered_dataset.csv
* dataset/FlakeFlagger/FlakeFlaggerFeaturesTypes.csv
* dataset/FlakeFlagger/Information_gain_per_feature.csv

### Output File:
* results/FlakeFlagger_black-box_results.csv
* results/FlakeFlagger_white-box_results.csv

### Replicating FlakeFlagger experiments
To run the FlakeFlagger experiments, navigate to the `src\` folder and run the following command:

```console
bash FlakeFlagger_predictor.sh white-box
bash FlakeFlagger_predictor.sh black-box
```

This will generate the classification results into `results/FlakeFlagger_white-box_results.csv` and `results/FlakeFlagger_black-box_results.csv` for the both white-box and black-box experiments, respectively.
