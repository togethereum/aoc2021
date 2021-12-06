(ns aoc2021.day05-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
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

(def test-input "
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
")

(deftest solve-1-test
  (let [input (str/split-lines test-input)]
    (is (= (solve-1 input)
           5))))
