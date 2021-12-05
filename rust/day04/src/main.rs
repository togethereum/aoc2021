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