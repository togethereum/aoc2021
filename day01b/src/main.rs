use std::fs::File;
use std::io::Read;

fn main() {
    let count = count_increasing_window_sums_in_file("input/input.txt");
    println!("{}", count);
}

fn sliding_window_sums_of_int_vec(vec: Vec<i32>, window_size: usize) -> Vec<i32> {
    vec.windows(window_size).map(|window| window.to_vec().iter().sum()).collect()
}

fn read_lines_from_file(filename: &str) -> Vec<String> {
    let mut f = File::open(filename).expect("file not found");
    let mut contents = String::new();
    f.read_to_string(&mut contents)
        .expect("something went wrong reading the file");
    contents.lines().map(|s| s.to_string()).collect()
}

fn string_vec_to_int_vector(string_vec: Vec<String>) -> Vec<i32> {
    let int_vec: Vec<i32> = string_vec.iter().map(|s| s.parse::<i32>().unwrap()).collect();
    int_vec
}

fn count_increasing_numbers(int_vec: Vec<i32>) -> i32 {
    let mut count = 0;
    let mut previous_number = int_vec[0];
    for number in int_vec {
        if number > previous_number {
            count += 1;
        }
        previous_number = number;
    }
    count
}

fn count_increasing_window_sums_in_file(filename: &str) -> i32 {
    let lines = read_lines_from_file(filename);
    let int_vec = string_vec_to_int_vector(lines);
    let window_size = 3;
    let sums = sliding_window_sums_of_int_vec(int_vec, window_size);
    let count = count_increasing_numbers(sums);
    count
}

#[test]
fn test_sliding_windows_of_int_vec() {
    assert_eq!(sliding_window_sums_of_int_vec(vec![1, 2, 3, 4, 5], 3), vec![6, 9, 12]);
}

#[test]
fn test_count_increasing_window_sums_in_file() {
    assert_eq!(count_increasing_window_sums_in_file("input/test.txt"), 5);
}