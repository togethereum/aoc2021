use std::fs::File;
use std::io::Read;

fn main() {
    let count = count_increasing_numbers_in_file("./input/input.txt");
    println!("{}", count);
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

fn count_increasing_numbers_in_file(filename: &str) -> i32 {
    let string_vec = read_lines_from_file(filename);
    let int_vec = string_vec_to_int_vector(string_vec);
    count_increasing_numbers(int_vec)
}

#[test]
fn test_count_increasing_numbers_in_file() {
    let count = count_increasing_numbers_in_file("./input/test.txt");
    assert_eq!(count, 7);
}