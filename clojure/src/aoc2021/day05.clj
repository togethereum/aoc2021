(ns aoc2021.day05
    (:require [clojure.string :as str]))

(defn parse-line
  [line]
  (map read-string (str/split line #",| -> ")))

(defn max-coords
  [coords]
  (let [[x1 y1 x2 y2] (apply map max coords)]
      [(max x1 x2) (max y1 y2)]))

(defn solve-1
  [lines])

(defn solve-2
  [lines])