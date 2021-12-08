(ns aoc2021.day08-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [aoc2021.core :refer [read-lines]]
            [aoc2021.day08 :refer :all]))

(deftest split-line-test
  (is (= (split-line "ab cd | ef gh")
        [["ab" "cd"] ["ef" "gh"]])))

(deftest count-in-line-test
  (is (= (count-in-line [:dummy ["a" "ab" "b" "abcd"]])
        3)))

(def test-input
  "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
  edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
  fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
  fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
  aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
  fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
  dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
  bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
  egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
  gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")

(deftest solve-1-test
  (is (= (solve-1 (str/split-lines test-input))
        26)))

(deftest solve-1-prod
  (let [str-lines (read-lines "prod/day08.txt")]
    (println (solve-1 str-lines))))
