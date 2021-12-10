(ns aoc2021.day10-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc2021.core :refer [read-lines]]
            [aoc2021.day10 :refer :all]))

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

(deftest solve-1-lines-prod
  (let [lines (read-lines "prod/day10.txt")]
    (println (solve-1-lines lines))))