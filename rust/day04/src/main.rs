fn main() {
    println!("Hello, world!");
}

struct BingoNumber {
    number: u8,
    is_called: bool,
}

struct Board {
    cells: Vec<Vec<BingoNumber>>,
    last_called: u8,
}

struct BingoGame {
    boards: Vec<Board>,
    drawn_numbers: Vec<u8>,
}

fn draw_number(board: &mut Board, number: u8) {
    let mut found = false;
    for row in 0..board.cells.len() {
        for col in 0..board.cells[row].len() {
            if board.cells[row][col].number == number {
                board.cells[row][col].is_called = true;
                found = true;
                break;
            }
        }
        if found {
            break;
        }
    }
    board.last_called = number;
}

fn is_bingo(board: &Board) -> bool {
    let mut found = true;
    for row in 0..board.cells.len() {
        found = true;
        for col in 0..board.cells[row].len() {
            if !board.cells[row][col].is_called {
                found = false;
                break;
            }
        }
        if found {
            return true;
        }
    }
    for col in 0..board.cells[0].len() {
        found = true;
        for row in 0..board.cells.len() {
            if !board.cells[row][col].is_called {
                found = false;
                break;
            }
        }
        if found {
            return true;
        }
    }
    return false;
}

fn calculate_score(board: &Board) -> u32 {
    let mut score = 0;
    for row in 0..board.cells.len() {
        for col in 0..board.cells[row].len() {
            if !board.cells[row][col].is_called {
                score += board.cells[row][col].number as u32;
            }
        }
    }
    return score * board.last_called as u32;
}

fn parse_bingo_game(input: Vec<String>) -> BingoGame {
    let mut bingo_game = BingoGame {
        boards: Vec::new(),
        drawn_numbers: Vec::new(),
    };
    bingo_game.drawn_numbers = input[0].split(",").map(|x| x.parse::<u8>().unwrap()).collect();

    let mut board = Board {
        cells: Vec::new(),
        last_called: 0,
    };
    for line in input[1..].iter() {
        if line.is_empty() {
            bingo_game.boards.push(board);
            board = Board {
                cells: Vec::new(),
                last_called: 0,
            };
            continue;
        }

        let row = line.split_whitespace().map(|x| BingoNumber {
            number: x.parse::<u8>().unwrap(),
            is_called: false,
        }).collect();
        board.cells.push(row);
    }
    bingo_game.boards.push(board);

    return bingo_game;
}

#[test]
fn test_is_bingo() {
    let mut board = Board {
        cells: vec![vec![BingoNumber {
            number: 1,
            is_called: true,
        }, BingoNumber {
            number: 2,
            is_called: false,
        }],
        vec![BingoNumber {
            number: 3,
            is_called: false,
        }, BingoNumber {
            number: 4,
            is_called: false,
        }]],
        last_called: 1,
    };
    assert!(!is_bingo(&board));

    board.cells[0][1].is_called = true;
    assert!(is_bingo(&board));

    board.cells[0][1].is_called = false;
    board.cells[1][0].is_called = true;
    assert!(is_bingo(&board));
}

#[test]
fn test_calculate_score() {
    let mut board = Board {
        cells: vec![vec![BingoNumber {
            number: 1,
            is_called: true,
        }, BingoNumber {
            number: 2,
            is_called: true,
        }],
                    vec![BingoNumber {
                        number: 3,
                        is_called: false,
                    }, BingoNumber {
                        number: 4,
                        is_called: false,
                    }]],
        last_called: 1,
    };
    board.last_called = 2;

    assert_eq!(calculate_score(&board), 2 * (3 + 4));
}

#[test]
fn test_drawing() {
    let mut board = Board {
        cells: vec![vec![BingoNumber {
            number: 1,
            is_called: false,
        }, BingoNumber {
            number: 2,
            is_called: false,
        }],
                    vec![BingoNumber {
                        number: 3,
                        is_called: false,
                    }, BingoNumber {
                        number: 4,
                        is_called: false,
                    }]],
        last_called: 1,
    };
    assert!(!is_bingo(&board));

    draw_number(&mut board, 1);
    assert!(!is_bingo(&board));

    draw_number(&mut board, 2);
    assert!(is_bingo(&board));
}

#[test]
fn test_parse_bingo_game() {
    let input = vec![
        "1,2".to_string(),
        "1 2".to_string(),
        "3 4".to_string(),
        "".to_string(),
        "1 3".to_string(),
        "2 4".to_string(),
        ].iter().map(|x| x.to_string()).collect();
    let bingo_game = parse_bingo_game(input);
    assert!(bingo_game.drawn_numbers.contains(&1));
    assert!(bingo_game.drawn_numbers.contains(&2));
    assert!(bingo_game.boards.len() == 2);
    assert!(bingo_game.boards[0].cells[0][0].number == 1);
}