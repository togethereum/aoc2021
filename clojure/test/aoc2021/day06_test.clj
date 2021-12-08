(ns aoc2021.day06-test
  (:require [clojure.test :refer :all]
            [aoc2021.day06 :refer :all]))

(deftest next-day-test
  (is (= (next-day [0 2 0])
         [6 1 6 8 8])))

(def test-input [3,4,3,1,2])

(deftest count-after-n-days-test
  (is (= (count-after-n-days 18 test-input)
        26))
  (is (= (count-after-n-days 80 test-input)
        5934)))

(deftest solve-1-test
  (println (solve-1)))

(deftest solve-2-test
  (println (solve-2)))
