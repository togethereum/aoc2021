use std::fs::File;
use std::io::Read;

fn main() {
    let (x, depth) = get_direction_pair("input.txt");
    println!("Puzzle A: {}", x * depth);
    let (x, depth) = get_direction_pair_with_aim("input.txt");
    println!("Puzzle B: {}", x * depth);
}

fn read_lines_from_file(filename: &str) -> Vec<String> {
    let mut f = File::open(filename).expect("file not found");
    let mut contents = String::new();
    f.read_to_string(&mut contents)
        .expect("something went wrong reading the file");
    contents.lines().map(|s| s.to_string()).collect()
}

fn get_direction_pair(filename: &str) -> (i32, i32) {
    let lines = read_lines_from_file(filename);
    let mut x = 0;
    let mut depth = 0;
    for line in lines {
        let step = parse_step(&line);
        match step.direction {
            Direction::Forward => { x += step.distance }
            Direction::Up => { depth -= step.distance }
            Direction::Down => { depth += step.distance }
        }
    }
    (x, depth)
}

enum Direction {
    Forward,
    Up,
    Down
}

fn new_direction(direction: &str) -> Direction {
    match direction {
        "forward" => Direction::Forward,
        "up" => Direction::Up,
        "down" => Direction::Down,
        _ => panic!("Unknown direction: {}", direction)
    }
}

struct Step {
    direction: Direction,
    distance: i32,
}

fn parse_step(line: &str) -> Step {
    let mut direction_and_distance= line.split_whitespace();
    let direction = direction_and_distance.next().unwrap();
    let distance = direction_and_distance.next().unwrap().parse::<i32>().unwrap();
    Step{direction: new_direction(direction), distance}
}

fn get_direction_pair_with_aim(filename: &str) -> (i32, i32) {
   let lines = read_lines_from_file(filename);
   let mut x = 0;
   let mut depth = 0;
    let mut aim = 0;
   for line in lines {
       let step = parse_step(&line);
       match step.direction {
           Direction::Forward => { x += step.distance; depth += step.distance * aim; }
           Direction::Up => { aim -= step.distance; }
           Direction::Down => { aim += step.distance; }
       }
   }
    (x, depth)
}

#[test]
fn test_get_direction_pair() {
    let (x, y) = get_direction_pair("test_input.txt");
    assert_eq!((x, y), (15, 10));
}

#[test]
fn test_get_direction_pair_with_aim() {
    let (x, y) = get_direction_pair_with_aim("test_input.txt");
    assert_eq!((x, y), (15, 60));
}
