dataset=$1
data_path="../dataset/${dataset}"
input_data_file="${data_path}/${dataset}_filtered_dataset.csv"
tests_cases_class_files="${data_path}/${dataset}_class_files/"
tests_cases_full_code_output="${data_path}/${dataset}_test_cases_full_code/"
test_cases_preprocessed_code_output="${data_path}/${dataset}_test_cases_preprocessed_code/"
output_file="${data_path}/Flakify_${dataset}_dataset.csv"

java -jar FlakifySmellsDetector.jar $input_data_file $tests_cases_class_files $tests_cases_full_code_output $test_cases_preprocessed_code_output $output_file