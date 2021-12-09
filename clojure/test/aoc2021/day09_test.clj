(ns aoc2021.day09-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc2021.core :refer [read-lines]]
            [aoc2021.day09 :refer :all]))

(def matrix [[1 2 3]
             [4 0 6]
             [7 8 9]])

(deftest neighbor-coords-test
  (is (= (neighbor-coords matrix 1 1))
    [[0 1] [1 0] [0 2] [2 0]])
  (is (= (neighbor-coords matrix 0 0))
    [[0 1] [1 0]]))

(deftest neighbors-test
  (is (= (neighbors matrix 1 1)
        [2 8 4 6])))

(deftest low-points-test
  (is (= (low-points matrix)
         [1 0])))

(def test-input "
2199943210
3987894921
9856789892
8767896789
9899965678
")

(deftest solve-1-test
  (is (= (solve-1 (str/split-lines test-input))
        15)))

(deftest solve-1-prod
  (let [str-lines (read-lines "prod/day09.txt")]
    (println (solve-1 str-lines))))
