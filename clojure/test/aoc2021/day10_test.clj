(ns aoc2021.day10-test
  (:require [clojure.test :refer :all]
            [aoc2021.day10 :refer :all]))

(deftest process-char-test
  (let [stack [\( \{]]
    (are [ch got] (= (process-char stack ch) got)
      \} [[\(] nil]
      \< [[\( \{ \<] nil]
      \] [stack \]])))
