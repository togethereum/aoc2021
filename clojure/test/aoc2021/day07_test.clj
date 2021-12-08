(ns aoc2021.day07-test
  (:require [clojure.test :refer :all]
            [aoc2021.day07 :refer :all]))

(def test-input [16,1,2,0,4,2,7,1,2,14])

(deftest cost-of-alignment-test
  (is (= (cost-of-alignment 2 test-input)
        37))
  (is (= (cost-of-alignment 3 test-input)
        39)))

(deftest solve-1-test
  (is (= (solve-1 test-input)
        37)))

(deftest solve-1-prod
  (println (solve-1 prod-input)))

(deftest increasing-cost-of-alignment-test
  (is (= (increasing-cost-of-alignment 2 test-input)
         206)))

(deftest solve-2-test
  (is (= (solve-2 test-input)
         168)))

(deftest solve-2-prod
  (println (solve-2 prod-input)))
