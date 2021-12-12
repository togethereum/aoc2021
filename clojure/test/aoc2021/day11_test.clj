(ns aoc2021.day11-test
  (:require [clojure.test :refer :all]
            [aoc2021.day11 :refer :all]))

(def matrix [[1 2 3]
             [4 0 6]
             [7 9 9]])

(deftest neighbor-coords-test
  (is (= (count (neighbor-coords matrix 1 1))
        8)))

(deftest flash-coords-test
  (is (= (flash-coords matrix)
        [[1 1] [2 0] [1 0] [1 2] [1 2] [1 1]])))


