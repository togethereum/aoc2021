use std::collections::HashMap;
use std::fs::File;
use std::io::Read;

fn main() {
    let (most_common, least_common) = most_and_least_common_binary_digits_from_file("input.txt");
    println!("{}", most_common * least_common);
}

fn read_lines_from_file(filename: &str) -> Vec<String> {
    let mut f = File::open(filename).expect("file not found");
    let mut contents = String::new();
    f.read_to_string(&mut contents)
        .expect("something went wrong reading the file");
    contents.lines().map(|s| s.to_string()).collect()
}

fn count_characters_at_index(v: &Vec<String>, index: usize) -> HashMap<char, usize> {
    let mut char_counts = HashMap::new();
    char_counts.insert('0', 0);
    char_counts.insert('1', 0);
    for line in v {
        let c = line.chars().nth(index).unwrap();
        let count = char_counts.entry(c).or_insert(0);
        *count += 1;
    }
    char_counts
}

fn most_common_binary_digit_at(v: &Vec<String>, index: usize, default_char: char) -> char {
    let counts = count_characters_at_index(v, index);
    if counts[&'0'] > counts[&'1'] {
        '0'
    } else if counts[&'1'] > counts[&'0'] {
        '1'
    } else {
        default_char
    }
}

fn binary_string_to_number(s: &str) -> u32 {
    isize::from_str_radix(s, 2).unwrap() as u32
}

fn complement_binary_character(c: char) -> char {
    match c {
        '0' => '1',
        '1' => '0',
        _ => panic!("invalid character"),
    }
}

fn most_and_least_common_binary_digits_from_file(filename: &str) -> (u32, u32) {
    let lines = read_lines_from_file(filename);
    let mut most_common_digits = String::new();
    let mut least_common_digits = String::new();
    for i in 0..lines[0].len() {
        let c = most_common_binary_digit_at(&lines, i, '0');
        most_common_digits.push(c);
        least_common_digits.push(complement_binary_character(c))
    }
    let most_common_number = binary_string_to_number(&most_common_digits);
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
        "1100".to_string(),
        "1110".to_string(),
        "1000".to_string(),
        "1000".to_string(),
    ];
    assert_eq!(most_common_binary_digit_at(&v, 0, '0'), '1');
    assert_eq!(most_common_binary_digit_at(&v, 1, '0'), '0');
}

#[test]
fn test_binary_string_to_number() {
    assert_eq!(binary_string_to_number("1101"), 13);
}
