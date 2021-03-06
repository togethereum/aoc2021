(ns aoc2021.day05-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc2021.core :refer [read-lines]]
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
  (is (= (points-of-line [1 2 3 2] false)
         [[1 2] [2 2] [3 2]]))
  (is (= (points-of-line [3 2 1 2] false)
         [[1 2] [2 2] [3 2]]))
  (is (= (points-of-line [1 2 1 4] false)
         [[1 2] [1 3] [1 4]]))
  (is (= (points-of-line [1 4 1 2] false)
         [[1 2] [1 3] [1 4]]))
  (is (= (points-of-line [1 3, 3 5] true)
         [[1 3] [2 4] [3 5]]))
  (is (= (points-of-line [3 5, 1 3] true)
         [[1 3] [2 4] [3 5]]))
  (is (= (points-of-line [0 4, 3 1] true)
         [[0 4] [1 3] [2 2] [3 1]])))

(deftest draw-line-test
  (let [m (init-matrix 3 4)]
    (is (= (draw-line m [0 1, 1 1] false)
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
  (let [str-lines (str/split-lines test-input)]
    (is (= (solve-1 str-lines)
          5))))

(deftest solve-1-prod
  (let [str-lines (read-lines "prod/day05.txt")]
    (println (solve-1 str-lines))))

(deftest solve-2-test
  (let [str-lines (str/split-lines test-input)]
    (is (= (solve-2 str-lines)
           12))))

(deftest solve-2-prod
  (let [str-lines (read-lines "prod/day05.txt")]
    (println (solve-2 str-lines))))
