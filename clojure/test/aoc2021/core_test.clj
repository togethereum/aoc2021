(ns aoc2021.core-test
  (:require [clojure.test :refer :all]
            [aoc2021.core :refer :all]))

(defn dummy [] 42)

(deftest a-test
  (testing "Resolve fn"
    (is (= 42 ((resolve-fn "aoc2021.core-test" "dummy"))))))
