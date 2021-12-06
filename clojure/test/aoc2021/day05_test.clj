(ns aoc2021.day05-test
  (:require [clojure.test :refer :all]
            [aoc2021.day05 :refer :all]))

(deftest parse-line-test
  (is (= (parse-line "1,2 -> 3,4")
         [1 2 3 4])))

(deftest max-coord-test
  (is (= (max-coords [[1 2 3 4] [0 0 0 0]])
         [3 4])))
