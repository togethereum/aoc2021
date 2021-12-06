(ns aoc2021.day05-test
  (:require [clojure.test :refer :all]
            [aoc2021.day05 :refer :all]))

(deftest parse-line-test
  (is (= (parse-line "1,2 -> 3,4")
         [1 2 3 4])))

(deftest max-coord-test
  (is (= (max-coords [[1 2 3 4] [0 0 0 0]])
         [3 4])))

(deftest init-matrix-test
  (is (= (init-matrix 3 4)
         [[0 0 0 0] [0 0 0 0] [0 0 0 0]])))

(deftest points-of-line-test
  (is (= (points-of-line [1 2 3 2])
         [[1 2] [2 2] [3 2]]))
  (is (= (points-of-line [3 2 1 2])
         [[1 2] [2 2] [3 2]]))
  (is (= (points-of-line [1 2 1 4])
         [[1 2] [1 3] [1 4]]))
  (is (= (points-of-line [1 4 1 2])
         [[1 2] [1 3] [1 4]])))

(deftest draw-line-test
  (let [m (init-matrix 3 4)]
    (is (= (draw-line m [0 1, 1 1])
           [[0 1 0 0]
            [0 1 0 0]
            [0 0 0 0]]))))
