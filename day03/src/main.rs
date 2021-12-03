use std::collections::HashMap;
use std::fs::File;
use std::io::Read;

fn main() {
    println!("Hello, world!");
}

fn read_lines_from_file(filename: &str) -> Vec<String> {
    let mut f = File::open(filename).expect("file not found");
    let mut contents = String::new();
    f.read_to_string(&mut contents)
        .expect("something went wrong reading the file");
    contents.lines().map(|s| s.to_string()).collect()
}

fn most_common_character_at(v: &Vec<String>, index: usize) -> char {
    let mut char_counts = HashMap::new();
    for line in v {
        let c = line.chars().nth(index).unwrap();
        let count = char_counts.entry(c).or_insert(0);
        *count += 1;
    }
    let mut max_count = 0;
    let mut max_char = ' ';
    for (c, count) in char_counts {
        if count > max_count {
            max_count = count;
            max_char = c;
        }
    }
    max_char
}

fn least_common_character_at(v: &Vec<String>, index: usize) -> char {
    let mut char_counts = HashMap::new();
    for line in v {
        let c = line.chars().nth(index).unwrap();
        let count = char_counts.entry(c).or_insert(0);
        *count += 1;
    }
    let mut min_count = std::usize::MAX;
    let mut min_char = ' ';
    for (c, count) in char_counts {
        if count < min_count {
            min_count = count;
            min_char = c;
        }
    }
    min_char
}

fn binary_string_to_number(s: &str) -> u32 {
    isize::from_str_radix(s, 2).unwrap() as u32
}

fn most_and_least_common_binary_digits_from_file(filename: &str) -> (u32, u32) {
    let lines = read_lines_from_file(filename);
    let mut most_common_digits = String::new();
    for i in 0..lines[0].len() {
        most_common_digits.push(most_common_character_at(&lines, i));
    }
    let most_common_number = binary_string_to_number(&most_common_digits);

    let mut least_common_digits = String::new();
    for i in 0..lines[0].len() {
        least_common_digits.push(least_common_character_at(&lines, i));
    }
    let least_common_number = binary_string_to_number(&least_common_digits);
    (most_common_number, least_common_number)
}

#[test]
fn test_most_and_least_common_binary_digits_from_file() {
    let (most_common, least_common) = most_and_least_common_binary_digits_from_file("test_input.txt");
    assert_eq!(most_common, 22);
    assert_eq!(least_common, 9);
}

#[test]
fn test_most_common_character_at() {
    let v = vec![
        "abcdefgh".to_string(),
        "bababc".to_string(),
        "abbcde".to_string(),
        "abcccd".to_string(),
        "aabcdd".to_string(),
        "abcdee".to_string(),
        "ababab".to_string(),
    ];
    assert_eq!(most_common_character_at(&v, 0), 'a');
    assert_eq!(most_common_character_at(&v, 1), 'b');
}

#[test]
fn test_binary_string_to_number() {
    assert_eq!(binary_string_to_number("1101"), 13);
}
