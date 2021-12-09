(ns aoc2021.day08
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn split-line
  [str-line]
  (let [[digits-str display-str] (str/split str-line #" \| " 2)
        digits (str/split digits-str #" ")
        display (str/split display-str #" ")]
    [digits display]))

(defn count-in-line
  [[digits display]]
  (->> display
       (map count)
       (filter #{2 3 4 7})
       count))

(defn solve-1
  [str-lines]
  (->> str-lines
       (remove str/blank?)
       (map (comp count-in-line split-line))
       (apply +)))

(defn has-count-and-is-subset
  [sets n subset superset]
  (first
    (filter #(and
               (= (count %) n)
               (or (not subset)
                   (set/subset? subset %))
               (or (not superset)
                   (set/subset? % superset)))
            sets)))

(def guess-order [1 4 7 8 3 9 0 6 5 2])

(def guess-logic
  [[6 0 nil]
   [2 nil nil]
   [5 nil nil]
   [5 7 nil]
   [4 nil nil]
   [5 nil 6]
   [6 nil nil]
   [3 nil nil]
   [7 nil nil]
   [6 3 nil]])

(defn guess-numbers
  [sets]
  (let [[paired _] (reduce
                       (fn [[paired unpaired] digit]
                         (let [[n subset-id superset-id] (get guess-logic digit)
                               subset (get paired subset-id)
                               superset (get paired superset-id)
                               matching-set (has-count-and-is-subset unpaired n subset superset)
                               new-paired (assoc paired digit matching-set)
                               new-unpaired (remove #(= % matching-set) unpaired)]
                          [new-paired new-unpaired]))
                       [{} sets]
                       guess-order)]
       (set/map-invert paired)))
