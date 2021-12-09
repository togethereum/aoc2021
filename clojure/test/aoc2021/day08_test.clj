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

(def sample-pairs-kw
 {:acedgfb 8
  :cdfbe 5
  :gcdfa 2
  :fbcad 3
  :dab 7
  :cefabd 9
  :cdfgeb 6
  :eafb 4
  :cagedb 0
  :ab 1})

(def sample-pairs
  (zipmap
    (map #(into #{} (name %))
         (keys sample-pairs-kw))
    (vals sample-pairs-kw)))

(deftest has-count-and-is-subset-test
  (let [sets [#{1 2} #{3 4} #{3 5} #{3} #{4}]]
    (is (= (has-count-and-is-subset sets 1 nil nil)
          #{3}))
    (is (= (has-count-and-is-subset sets 2 #{3} nil)
          #{3 4}))
    (is (= (has-count-and-is-subset sets 2 nil #{1 3 5})
          #{3 5}))))

(deftest guess-numbers-test
  (is (= (guess-numbers (keys sample-pairs))
        sample-pairs)))

(deftest merge-digits-test
  (is (= (merge-digits [2 3 5])
        235)))

(deftest solve-line-test
  (is (= (solve-line "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
        5353)))

(deftest solve-2-test
  (is (= (solve-2 (str/split-lines test-input))
         61229)))


