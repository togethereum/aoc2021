(ns aoc2021.day10-test
  (:require [clojure.test :refer :all]
            [aoc2021.day10 :refer :all]
            [clojure.string :as str]))

(deftest process-char-test
  (let [stack [\( \{]]
    (are [ch got] (= (process-char stack ch) got)
      \} [[\(] nil]
      \< [[\( \{ \<] nil]
      \] [stack \]])))

(deftest process-line-test
  (are [line want] (= (process-line line) want)
    "[<>]" [[] nil]
    "[<" [[\[ \<] nil]
    "[}" [[\[] \}]
    "[]<>" [[] nil]
    "<>]" [[] \]]))

(def test-input
  "[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]")

(deftest solve-1-lines-test
  (is (= (solve-1-lines (str/split-lines test-input))
        26397)))