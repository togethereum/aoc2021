(ns aoc2021.day08
  (:require [clojure.string :as str]))

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
