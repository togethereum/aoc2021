(ns aoc2021.day09-test
  (:require [clojure.test :refer :all]
            [aoc2021.day09 :refer :all]))

(deftest neighbor-coords-test
  (let [m (repeat 3 (repeat 3 :foo))]
    (is (= (neighbor-coords m 1 1))
      [[0 1] [1 0] [0 2] [2 0]])
    (is (= (neighbor-coords m 0 0))
      [[0 1] [1 0]])))
